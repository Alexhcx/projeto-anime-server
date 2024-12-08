package com.clienteservidor.animeserver.animeserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clienteservidor.animeserver.animeserver.models.OrdersProductsModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersProductsDAO extends JpaRepository<OrdersProductsModel, Long> {

    List<OrdersProductsModel> findByOrderId(Long orderId);

    void deleteByOrderIdAndProductId(Long orderId, Long productId);

    Optional<OrdersProductsModel> findByOrderIdAndProductId(Long orderId, Long productId);
    ;
}
