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
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class GroceryServiceImpl implements GroceryService {

    @Inject
    GroceryRepository groceryRepository;

    @Inject
    BookingDetailsRepository bookingDetailsRepository;

    @Inject
    GroceryMapper groceryMapper;

    @Override
    @Transactional
    public RegisterGroceryItemsResponse registerGrocery(RegisterGroceryItemsRequest registerGroceryItemsRequest) {
        requireValidGroceryItem(registerGroceryItemsRequest.getItemName());
        Item item = persistGroceryItems(registerGroceryItemsRequest);
        return groceryMapper.mapToRegisterGroceryItemsResponse(item);
    }

    @Override
    @Transactional
    public List<RetrieveGroceryItemResponse> retrieveGroceryItem() {
        List<Item> item = groceryRepository.findAll().list();
        return groceryMapper.mapToRetrieveGroceryItemResponse(item);
    }

    @Override
    @Transactional
    public Response deleteGroceryItem(String itemName) {
        groceryRepository.deleteByItemName(itemName);
        return Response.noContent().build();
    }

    @Override
    public UpdateGroceryItemResponse updateGroceryItem(String itemName,
                                                       UpdateGroceryItemsRequest updateGroceryItemsRequest) {
        Item itemFromRepository = groceryRepository.findByName(itemName)
                .orElseThrow(() -> Errors.groceryItemDoesNotExist(itemName));

        Item itemFromRequest = groceryMapper.mapToItemDomain(updateGroceryItemsRequest);

        groceryMapper.mapToItemDomain(itemFromRepository, itemFromRequest);
        return groceryMapper.mapToUpdateGroceryItemResponse(itemFromRepository);
    }

    @Override
    public List<RetrieveGroceryItemResponse> retrieveAvailableGroceryItem() {
        List<Item> item = groceryRepository.listAllAvailableItems();
        return groceryMapper.mapToRetrieveGroceryItemResponse(item);
    }

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

        return groceryMapper.mapToBookingDetailsResponse(bookingDetails);
    }

    private void requireValidGroceryItem(String itemName) {
        if (groceryRepository.findByName(itemName).isPresent()) {
            throw Errors.duplicateGroceryItem(itemName);
        }
    }

    private BookingDetails persistBookingDetails(BookGroceryItemsRequest bookGroceryItemsRequest,
                                                 int billAmount) {
        BookingDetails bookingDetails =
                groceryMapper.mapToBookingDetailsDomain(UUID.randomUUID(), bookGroceryItemsRequest, billAmount);
        bookingDetailsRepository.persist(bookingDetails);
        return bookingDetails;
    }

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

    private void processRequestedGroceryItem(Item groceryItem, GroceryItems requestedItems, AtomicInteger billAmount, List<GroceryExceptionMessage> errorMessage) {
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

    private void populateErrorMessage(List<GroceryExceptionMessage> errorMessage, String errorCode, String message) {
        errorMessage.add(new GroceryExceptionMessage("BusinessValidationError", errorCode, message));
    }

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
