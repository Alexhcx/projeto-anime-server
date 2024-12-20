package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clienteservidor.animeserver.animeserver.models.PaymentModel;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDAO extends JpaRepository<PaymentModel, Long> {

  Optional<PaymentModel> findByOrderId(Long orderId);

  List<PaymentModel> findByMetodoPagamento(String metodoPagamento);

  List<PaymentModel> findByStatusPagamento(String statusPagamento);
}
