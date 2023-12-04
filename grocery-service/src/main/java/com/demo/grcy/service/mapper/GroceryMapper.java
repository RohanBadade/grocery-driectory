package com.demo.grcy.service.mapper;

import com.demo.grcy.model.dto.*;
import com.demo.grcy.model.enumeration.GroceryStatus;
import com.demo.grcy.service.domain.BookingDetails;
import com.demo.grcy.service.domain.Item;
import com.demo.grcy.service.domain.PurchasedGoods;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

/**
 * Grocery Mapper Class.
 * */
@Mapper(componentModel = "cdi", imports = GroceryStatus.class)
public interface GroceryMapper {

    /**
     * Maps Item domain to RegisterGroceryItemsResponse dto.
     *
     * @param item domain object containing grocery item details
     * @return RegisterGroceryItemsResponse dto containing registered grocery item details
     **/
    RegisterGroceryItemsResponse mapToRegisterGroceryItemsResponse(Item item);

    /**
     * Maps RegisterGroceryItemsRequest dto to Item domain.
     *
     * @param registerGroceryItemsRequest dto object containing grocery item details
     * @return Item domain object containing grocery item details
     **/
    @Mapping(target = "itemPrice" , source = "price")
    @Mapping(target = "numberOfAvailableItems", source = "quantity")
    @Mapping(target = "status", expression = "java(GroceryStatus.IN_STOCK)")
    Item mapToItemDomain(RegisterGroceryItemsRequest registerGroceryItemsRequest);

    /**
     * Maps GroceryItems dto to PurchasedGoods domain.
     *
     * @param groceryItems dto object containing requested item details
     * @return PurchasedGoods domain object containing purchased item details
     **/
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "numberOfPurchasedUnits", source = "quantity")
    @Named("groceryItems")
    PurchasedGoods mapToGroceryItems(GroceryItems groceryItems);

    /**
     * Maps BookGroceryItemsRequest dto to BookingDetails domain.
     *
     * @param bookGroceryItemsRequest dto object containing requested booking details
     * @param billAmount total bill amount
     * @return BookingDetails domain object containing booked item details
     **/
    @IterableMapping(qualifiedByName = "groceryItems")
    @Mapping(target = "purchasedGoods", source = "bookGroceryItemsRequest.groceryItemsList", qualifiedByName = "groceryItems")
    BookingDetails mapToBookingDetailsDomain(UUID receiptId, BookGroceryItemsRequest bookGroceryItemsRequest, int billAmount);

    /**
     * Maps UpdateGroceryItemsRequest dto to Item domain.
     *
     * @param itemName grocery item name
     * @param updateGroceryItemsRequest dto object containing update grocery item details
     * @return Item domain object containing updated grocery item details
     **/
    @Mapping(target = "itemPrice" , source = "updateGroceryItemsRequest.price")
    @Mapping(target = "numberOfAvailableItems", source = "updateGroceryItemsRequest.quantity")
    @Mapping(target = "status", expression = "java(GroceryStatus.IN_STOCK)")
    Item mapToItemDomain(String itemName, UpdateGroceryItemsRequest updateGroceryItemsRequest);

    /**
     * Maps list of Item domain to list of RetrieveGroceryItemResponse dto.
     *
     * @param item list of domain object containing grocery item details
     * @return list of RetrieveGroceryItemResponse dto object containing grocery item details
     **/
    List<RetrieveGroceryItemResponse> mapToRetrieveGroceryItemResponse(List<Item> item);

    /**
     * Maps Item domain from request to Item domain from repository.
     *
     * @param itemFromRepository domain object containing grocery item details from repository
     * @param itemFromRequest domain object containing grocery item details to update
     * @return Item domain object containing updated grocery item details
     **/
    Item mapToItemDomain(@MappingTarget Item itemFromRepository, Item itemFromRequest);

    /**
     * Maps Item domain to UpdateGroceryItemResponse dto.
     *
     * @param itemFromRepository domain object containing grocery item details
     * @return UpdateGroceryItemResponse dto object containing updated grocery item details
     **/
    UpdateGroceryItemResponse mapToUpdateGroceryItemResponse(Item itemFromRepository);

    /**
     * Maps PurchasedGoods domain to PurchasedGroceryItems dto.
     *
     * @param purchasedGoods domain object containing purchased item details
     * @return PurchasedGroceryItems dto object containing purchased item details
     **/
    @Mapping(target = "itemName", source = "itemName")
    @Mapping(target = "numberOfUnitsPurchased", source = "numberOfPurchasedUnits")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Named("mapToPurchasedGroceryItems")
    PurchasedGroceryItems mapToPurchasedGroceryItems(PurchasedGoods purchasedGoods);

    /**
     * Maps BookingDetails domain to BookGroceryItemsResponse dto.
     *
     * @param bookingDetails domain object containing booked item details
     * @return BookGroceryItemsResponse dto object containing booked item details
     **/
    @Mapping(target = "totalBillAmount", source = "bookingDetails.billAmount")
    @Mapping(target = "purchasedGroceryItemsList", source = "bookingDetails.purchasedGoods", qualifiedByName = "mapToPurchasedGroceryItems")
    BookGroceryItemsResponse mapToBookingDetailsResponse(BookingDetails bookingDetails);
}