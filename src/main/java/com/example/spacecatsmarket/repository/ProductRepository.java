package com.example.spacecatsmarket.repository;

import com.example.spacecatsmarket.repository.entity.ProductEntity;
import com.example.spacecatsmarket.repository.projection.PopularProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query("SELECT p.name AS name, SUM(e.amount) AS totalSold " +
            "FROM ProductEntity p JOIN OrderEntryEntity e ON p.id = e.product.id " +
            "GROUP BY p.name ORDER BY totalSold DESC")
    List<PopularProduct> findPopularProducts();

    Optional<ProductEntity> findByName(String name);
}