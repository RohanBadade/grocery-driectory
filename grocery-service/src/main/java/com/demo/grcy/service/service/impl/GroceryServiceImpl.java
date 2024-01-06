package com.demo.grcy.service.service.impl;

import com.demo.grcy.model.constant.ErrorCodes;
import com.demo.grcy.model.constant.ErrorMessages;
import com.demo.grcy.model.dto.*;
import com.demo.grcy.service.constants.Errors;
import com.demo.grcy.service.domain.BookingDetails;
import com.demo.grcy.service.domain.Item;
import com.demo.grcy.service.mapper.GroceryMapper;
import com.demo.grcy.service.repository.BookingDetailsRepository;
import com.demo.grcy.service.repository.GroceryRepository;
import com.demo.grcy.service.service.GroceryService;
import com.demo.grcy.service.util.BusinessValidationException;
import com.demo.grcy.service.util.dto.GroceryExceptionMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Grocery Service Implementation class implementing GroceryService interface.
 **/
@ApplicationScoped
public class GroceryServiceImpl implements GroceryService {

    @Inject
    GroceryRepository groceryRepository;

    @Inject
    BookingDetailsRepository bookingDetailsRepository;

    @Inject
    GroceryMapper groceryMapper;

    /**
     * Register's Grocery item
     *
     * @param registerGroceryItemsRequest object containing grocery details
     * @return RegisterGroceryItemsResponse object containing grocery details
     **/
    @Override
    @Transactional
    public RegisterGroceryItemsResponse registerGrocery(RegisterGroceryItemsRequest registerGroceryItemsRequest) {
        requireValidGroceryItem(registerGroceryItemsRequest.getItemName());
        Item item = persistGroceryItems(registerGroceryItemsRequest);
        return groceryMapper.mapToRegisterGroceryItemsResponse(item);
    }

    /**
     * Retrieves list of grocery items.
     *
     * @return list of object containing grocery details
     **/
    @Override
    @Transactional
    public List<RetrieveGroceryItemResponse> retrieveGroceryItem() {
        List<Item> item = groceryRepository.findAll().list();
        return groceryMapper.mapToRetrieveGroceryItemResponse(item);
    }

    /**
     * Deletes a grocery item.
     *
     * @param itemName grocery item name which is to be deleted
     **/
    @Override
    @Transactional
    public void deleteGroceryItem(String itemName) {
        groceryRepository.deleteByItemName(itemName);
    }

    /**
     * Updates grocery item details.
     *
     * @param itemName                  grocery item name whose details needs to be updated
     * @param updateGroceryItemsRequest object containing update grocery item details
     * @return object containing updated grocery item details
     **/
    @Override
    @Transactional
    public UpdateGroceryItemResponse updateGroceryItem(String itemName,
                                                       UpdateGroceryItemsRequest updateGroceryItemsRequest) {
        Item itemFromRepository = groceryRepository.findByName(itemName)
                .orElseThrow(() -> Errors.groceryItemDoesNotExist(itemName));

        Item itemFromRequest = groceryMapper.mapToItemDomain(itemName, updateGroceryItemsRequest);

        itemFromRepository = groceryMapper.mapToItemDomain(itemFromRepository, itemFromRequest);
        return groceryMapper.mapToUpdateGroceryItemResponse(itemFromRepository);
    }

    /**
     * Retrieves a list of available grocery item details.
     *
     * @return list of object containing available grocery item details.
     **/
    @Override
    public List<RetrieveGroceryItemResponse> retrieveAvailableGroceryItem() {
        List<Item> item = groceryRepository.listAllAvailableItems();
        return groceryMapper.mapToRetrieveGroceryItemResponse(item);
    }

    /**
     * Books grocery items.
     *
     * @param bookGroceryItemsRequest object containing booking details
     * @return object containing booked grocery item details.
     **/
    @Override
    @Transactional
    public BookGroceryItemsResponse bookGroceryItems(BookGroceryItemsRequest bookGroceryItemsRequest) {

        List<GroceryExceptionMessage> errorMessage = new ArrayList<>();

        AtomicInteger billAmount = new AtomicInteger();

        List<Item> item = groceryRepository.listAllAvailableItems();

        Map<String, Item> availableGroceryItems =
                item.stream().collect(Collectors.toMap(Item::getItemName, Function.identity()));

        processRequestedGroceryItemList(bookGroceryItemsRequest, availableGroceryItems, errorMessage, billAmount);

        if (!errorMessage.isEmpty()) {
            throw new BusinessValidationException(errorMessage);
        }

        BookingDetails bookingDetails = persistBookingDetails(bookGroceryItemsRequest, billAmount.get());

        // TODO - publish kafka event for email generation to send bill receipt for validated emails..

        return groceryMapper.mapToBookingDetailsResponse(bookingDetails);
    }

    /**
     * Checks whether grocery item exist or not.
     *
     * @param itemName grocery item name
     **/
    private void requireValidGroceryItem(String itemName) {
        if (groceryRepository.findByName(itemName).isPresent()) {
            throw Errors.duplicateGroceryItem(itemName);
        }
    }

    /**
     * Persist booked grocery items.
     *
     * @param bookGroceryItemsRequest object containing booking details
     * @param billAmount              total bill amount
     * @return object containing booking details.
     **/
    private BookingDetails persistBookingDetails(BookGroceryItemsRequest bookGroceryItemsRequest,
                                                 int billAmount) {
        BookingDetails bookingDetails =
                groceryMapper.mapToBookingDetailsDomain(UUID.randomUUID(), bookGroceryItemsRequest, billAmount);
        bookingDetailsRepository.persist(bookingDetails);
        return bookingDetails;
    }

    /**
     * This method processes requested booking details containing list of grocery item with available grocery items.
     *
     * @param bookGroceryItemsRequest object containing booking details
     * @param availableGroceryItems   map containing key value pair with Item name as key and item details as value
     * @param errorMessage            list of error messages
     * @param billAmount              total bill amount
     **/
    private void processRequestedGroceryItemList(BookGroceryItemsRequest bookGroceryItemsRequest,
                                                 Map<String, Item> availableGroceryItems,
                                                 List<GroceryExceptionMessage> errorMessage,
                                                 AtomicInteger billAmount) {
        bookGroceryItemsRequest
                .getGroceryItemsList()
                .forEach(requestedItems -> {
                    if (availableGroceryItems.containsKey(requestedItems.getItemName())) {
                        Item groceryItem = availableGroceryItems.get(requestedItems.getItemName());
                        processRequestedGroceryItem(groceryItem, requestedItems, billAmount, errorMessage);
                    } else {
                        populateErrorMessage(errorMessage, ErrorCodes.GROCERY_ITEM_DOES_NOT_EXIST,
                                String.format(ErrorMessages.GROCERY_ITEM_DOES_NOT_EXIST, requestedItems.getItemName()));
                    }
                });
    }

    /**
     * This method updates available grocery items after processing requested booking details.
     *
     * @param groceryItem    object containing grocery item details
     * @param requestedItems object containing requested grocery item
     * @param errorMessage   list of error messages
     * @param billAmount     total bill amount
     **/
    private void processRequestedGroceryItem(Item groceryItem,
                                             GroceryItems requestedItems,
                                             AtomicInteger billAmount,
                                             List<GroceryExceptionMessage> errorMessage) {
        if (requestedItems.getQuantity() <= groceryItem.getNumberOfAvailableItems()) {
            requestedItems.updateTotalPrice(groceryItem.getItemPrice());
            groceryItem.updateNumberOfAvailableItems(requestedItems.getQuantity());
            groceryItem.updateNumberOfSoldItems(requestedItems.getQuantity());
            billAmount.addAndGet((requestedItems.getQuantity() * groceryItem.getItemPrice()));
        } else {
            populateErrorMessage(errorMessage, ErrorCodes.NUMBER_OF_AVAILABLE_ITEMS_LESS_THAN_REQUESTED_QUANTITY,
                    String.format(ErrorMessages.NUMBER_OF_AVAILABLE_ITEMS_LESS_THAN_REQUESTED_QUANTITY, requestedItems.getItemName()));
        }
    }

    /**
     * Populates business validation error message.
     *
     * @param errorMessage list of error messages
     * @param errorCode    error code corresponding to error message
     * @param message      error message
     **/
    private void populateErrorMessage(List<GroceryExceptionMessage> errorMessage, String errorCode, String message) {
        errorMessage.add(new GroceryExceptionMessage("BusinessValidationError", errorCode, message));
    }

    /**
     * Persists grocery items
     *
     * @param registerGroceryItemsRequest object containing grocery items to persist
     * @return domain object containing grocery item details
     **/
    private Item persistGroceryItems(RegisterGroceryItemsRequest registerGroceryItemsRequest) {
        Item item = groceryMapper.mapToItemDomain(registerGroceryItemsRequest);
        try {
            groceryRepository.persist(item);
            return item;
        } catch (PersistenceException pe) {
            throw Errors.failedToPersistGroceryItems();
        }
    }
}
