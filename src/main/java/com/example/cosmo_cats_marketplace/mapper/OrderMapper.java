package com.example.cosmo_cats_marketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.cosmo_cats_marketplace.domain.Order;
import com.example.cosmo_cats_marketplace.dto.Order.OrderDto;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "products", source = "products")
    OrderDto toOrderDto(Order order);
}
