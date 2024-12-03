package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.animeserver.animeserver.dao.PaymentDAO;
import com.clienteservidor.animeserver.animeserver.models.PaymentModel;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {

  @Autowired
  private PaymentDAO paymentDAO;

  public PaymentModel criarPagamento(PaymentModel pagamento) {
    return paymentDAO.save(pagamento);
  }

  public PaymentModel atualizarPagamento(PaymentModel pagamento) {
    return paymentDAO.save(pagamento);
  }

  public void deletarPagamento(Long id) {
    paymentDAO.deleteById(id);
  }

  public List<PaymentModel> listarTodosOsPagamentos() {
    return paymentDAO.findAll();
  }

  public PaymentModel buscarPagamentoPorId(Long id) {
    return paymentDAO.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com o ID: " + id));
  }

  public PaymentModel buscarPagamentoPorPedidoId(Long orderId) {
    return paymentDAO.findByOrderId(orderId)
        .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado para o pedido com o ID: " + orderId));
  }

  public List<PaymentModel> buscarPagamentosPorMetodoPagamento(String metodoPagamento) {
    return paymentDAO.findByMetodoPagamento(metodoPagamento);
  }

  public List<PaymentModel> buscarPagamentosPorStatusPagamento(String statusPagamento) {
    return paymentDAO.findByStatusPagamento(statusPagamento);
  }
}
