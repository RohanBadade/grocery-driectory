package com.demo.grcy.service.mapper;

import com.demo.grcy.model.dto.*;
import com.demo.grcy.model.enumeration.GroceryStatus;
import com.demo.grcy.service.domain.BookingDetails;
import com.demo.grcy.service.domain.Item;
import com.demo.grcy.service.domain.PurchasedGoods;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "cdi", imports = GroceryStatus.class)
public interface GroceryMapper {

    RegisterGroceryItemsResponse mapToRegisterGroceryItemsResponse(Item item);

    @Mapping(target = "itemPrice" , source = "price")
    @Mapping(target = "numberOfAvailableItems", source = "quantity")
    @Mapping(target = "status", expression = "java(GroceryStatus.IN_STOCK)")
    Item mapToItemDomain(RegisterGroceryItemsRequest registerGroceryItemsRequest);

    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "numberOfPurchasedUnits", source = "quantity")
    @Named("groceryItems")
    PurchasedGoods mapToGroceryItems(GroceryItems groceryItems);

    @IterableMapping(qualifiedByName = "groceryItems")
    @Mapping(target = "purchasedGoods", source = "bookGroceryItemsRequest.groceryItemsList", qualifiedByName = "groceryItems")
    BookingDetails mapToBookingDetailsDomain(UUID receiptId, BookGroceryItemsRequest bookGroceryItemsRequest, int billAmount);

    @Mapping(target = "itemPrice" , source = "price")
    Item mapToItemDomain(UpdateGroceryItemsRequest updateGroceryItemsRequest);

    List<RetrieveGroceryItemResponse> mapToRetrieveGroceryItemResponse(List<Item> item);

    void mapToItemDomain(@MappingTarget Item itemFromRepository, Item itemFromRequest);

    UpdateGroceryItemResponse mapToUpdateGroceryItemResponse(Item itemFromRepository);


    @Mapping(target = "itemName", source = "itemName")
    @Mapping(target = "numberOfUnitsPurchased", source = "numberOfPurchasedUnits")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Named("mapToPurchasedGroceryItems")
    PurchasedGroceryItems mapToPurchasedGroceryItems(PurchasedGoods purchasedGoods);

    @Mapping(target = "totalBillAmount", source = "bookingDetails.billAmount")
    @Mapping(target = "purchasedGroceryItemsList", source = "bookingDetails.purchasedGoods", qualifiedByName = "mapToPurchasedGroceryItems")
    BookGroceryItemsResponse mapToBookingDetailsResponse(BookingDetails bookingDetails);
}