package com.example.cosmo_cats_marketplace.mapper;

import com.example.cosmo_cats_marketplace.domain.Product;
import com.example.cosmo_cats_marketplace.dto.Product.ProductDto;
import com.example.cosmo_cats_marketplace.dto.Product.ProductEntry;
import com.example.cosmo_cats_marketplace.dto.Product.ProductListDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "manufacturer", source = "manufacturer")
  @Mapping(target = "category", source = "category")
  ProductDto toProductDto(Product product);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "manufacturer", source = "manufacturer")
  @Mapping(target = "category", source = "category.name")
  ProductEntry toProductEntry(Product product);

  default ProductListDto toProductListDto(List<Product> products) {
    return ProductListDto.builder().products(toProductEntries(products)).build();
  }

  List<ProductEntry> toProductEntries(List<Product> product);

  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "manufacturer", source = "manufacturer")
  @Mapping(target = "category", source = "category")
  Product toProduct(ProductDto productDto);
}
