package com.clienteservidor.animeserver.animeserver.dao.ordersdao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;

public interface OrdersDAO {

    // Create
    OrdersModel save(OrdersModel order);
    void addProductsToOrder(Long orderId, List<ProductModel> products);

    // Read
    Optional<OrdersModel> findById(Long id);
    List<OrdersModel> findByUserId(Long userId); // Buscar pedidos de um usuário
    List<OrdersModel> findByStatus(String status); // Buscar pedidos por status
    List<OrdersModel> findAll();
    List<ProductModel> findProductsByOrderId(Long orderId);

    // Update
    OrdersModel update(OrdersModel order);

    // Delete
    void deleteById(Long id);
    void removeProductsFromOrder(Long orderId, List<ProductModel> products);
}
