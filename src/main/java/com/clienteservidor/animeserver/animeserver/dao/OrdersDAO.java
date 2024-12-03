package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;

@Repository
public interface OrdersDAO extends JpaRepository<OrdersModel, Long> {

    List<OrdersModel> findByUserId(Long userId);

    List<OrdersModel> findByStatus(String status);

    @Query("SELECT p FROM ProductModel p JOIN p.orders o WHERE o.id = :orderId")
    List<ProductModel> findProductsByOrderId(@Param("orderId") Long orderId);

    @Modifying
    @Query(value = "INSERT INTO orders_products (order_id, product_id) VALUES (:orderId, :productId)", nativeQuery = true)
    void addProductToOrder(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Modifying
    @Query(value = "DELETE FROM orders_products WHERE order_id = :orderId AND product_id = :productId", nativeQuery = true)
    void removeProductFromOrder(@Param("orderId") Long orderId, @Param("productId") Long productId);
}
