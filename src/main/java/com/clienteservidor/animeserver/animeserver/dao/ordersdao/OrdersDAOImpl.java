package com.clienteservidor.animeserver.animeserver.dao.ordersdao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class OrdersDAOImpl implements OrdersDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrdersModel save(OrdersModel order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public Optional<OrdersModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(OrdersModel.class, id));
    }

    @Override
    public List<OrdersModel> findByUserId(Long userId) {
        return entityManager.createQuery("SELECT o FROM OrdersModel o WHERE o.user.id = :userId", OrdersModel.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<OrdersModel> findByStatus(String status) {
        return entityManager.createQuery("SELECT o FROM OrdersModel o WHERE o.status = :status", OrdersModel.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<OrdersModel> findAll() {
        return entityManager.createQuery("SELECT o FROM OrdersModel o", OrdersModel.class).getResultList();
    }

    @Override
    public OrdersModel update(OrdersModel order) {
        return entityManager.merge(order);
    }

    @Override
    public void deleteById(Long id) {
        OrdersModel order = entityManager.getReference(OrdersModel.class, id);
        entityManager.remove(order);
    }

    @Override
    public void addProductsToOrder(Long orderId, List<ProductModel> products) {
        OrdersModel order = entityManager.find(OrdersModel.class, orderId);
        order.getProducts().addAll(products);
        entityManager.merge(order);
    }

    @Override
    public void removeProductsFromOrder(Long orderId, List<ProductModel> products) {
        OrdersModel order = entityManager.find(OrdersModel.class, orderId);
        order.getProducts().removeAll(products);
        entityManager.merge(order);
    }

    @Override
    public List<ProductModel> findProductsByOrderId(Long orderId) {
        return entityManager.createQuery(
                        "SELECT p FROM ProductModel p JOIN p.orders o WHERE o.id = :orderId", ProductModel.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
}
