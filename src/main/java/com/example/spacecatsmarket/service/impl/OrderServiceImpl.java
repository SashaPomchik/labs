package com.example.spacecatsmarket.service.impl;

import com.example.spacecatsmarket.common.ProductType;
import com.example.spacecatsmarket.domain.order.Order;
import com.example.spacecatsmarket.domain.order.OrderEntry;
import com.example.spacecatsmarket.dto.order.PlaceOrderRequestDto;
import com.example.spacecatsmarket.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public Order placeOrder(UUID cartId, String customerReference, PlaceOrderRequestDto requestDto) {
        log.info("Creating order for cart: {}, customer: {}", cartId, customerReference);

        List<OrderEntry> entries = requestDto.getEntries().stream()
                .map(entryDto -> OrderEntry.builder()
                        .productType(ProductType.valueOf(entryDto.getProductType()))
                        .amount(entryDto.getAmount())
                        .build())
                .toList();

        return createOrder(cartId, entries, requestDto.getTotalPrice(), customerReference);
    }

    private Order createOrder(UUID cartId, List<OrderEntry> entries, Double totalPrice, String customerReference) {
        return Order.builder()
                .id(UUID.randomUUID())
                .cartId(cartId)
                .entries(entries)
                .totalPrice(totalPrice)
                .consumerReference(customerReference)
                .build();
    }
}
