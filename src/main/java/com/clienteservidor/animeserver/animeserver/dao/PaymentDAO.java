package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.PaymentModel;

@Repository
public interface PaymentDAO extends JpaRepository<PaymentModel, Long> {

  Optional<PaymentModel> findByOrderId(Long orderId);

  List<PaymentModel> findByMetodoPagamento(String metodoPagamento);

  List<PaymentModel> findByStatusPagamento(String statusPagamento);
}
