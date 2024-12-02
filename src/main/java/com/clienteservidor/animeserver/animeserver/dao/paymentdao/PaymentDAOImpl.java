package com.clienteservidor.animeserver.animeserver.dao.paymentdao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.PaymentModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PaymentModel save(PaymentModel payment) {
        entityManager.persist(payment);
        return payment;
    }

    @Override
    public Optional<PaymentModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(PaymentModel.class, id));
    }

    @Override
    public Optional<PaymentModel> findByOrderId(Long orderId) {
        return entityManager.createQuery("SELECT p FROM PaymentModel p WHERE p.order.id = :orderId", PaymentModel.class)
                .setParameter("orderId", orderId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<PaymentModel> findByMetodoPagamento(String metodoPagamento) {
        return entityManager.createQuery("SELECT p FROM PaymentModel p WHERE p.metodoPagamento = :metodoPagamento", PaymentModel.class)
                .setParameter("metodoPagamento", metodoPagamento)
                .getResultList();
    }

    @Override
    public List<PaymentModel> findByStatusPagamento(String statusPagamento) {
        return entityManager.createQuery("SELECT p FROM PaymentModel p WHERE p.statusPagamento = :statusPagamento", PaymentModel.class)
                .setParameter("statusPagamento", statusPagamento)
                .getResultList();
    }

    @Override
    public List<PaymentModel> findAll() {
        return entityManager.createQuery("SELECT p FROM PaymentModel p", PaymentModel.class).getResultList();
    }

    @Override
    public PaymentModel update(PaymentModel payment) {
        return entityManager.merge(payment);
    }

    @Override
    public void deleteById(Long id) {
        PaymentModel payment = entityManager.getReference(PaymentModel.class, id);
        entityManager.remove(payment);
    }
}
