package com.clienteservidor.animeserver.animeserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.OrdersProductsModel;

@Repository
public interface OrdersProductsDAO extends JpaRepository<OrdersProductsModel, Long> {
    
}
