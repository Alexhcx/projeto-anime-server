package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.animeserver.animeserver.dao.PaymentDAO;
import com.clienteservidor.animeserver.animeserver.models.PaymentModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Transactional
    public PaymentModel criarPagamento(PaymentModel pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("O pagamento não pode ser nulo.");
        }
        if (pagamento.getOrder() == null) {
            throw new IllegalArgumentException("O pedido associado ao pagamento não pode ser nulo.");
        }
        return paymentDAO.save(pagamento);
    }

    @Transactional
    public PaymentModel atualizarPagamento(PaymentModel pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("O pagamento não pode ser nulo.");
        }
        if (pagamento.getId() == null) {
            throw new IllegalArgumentException("O ID do pagamento não pode ser nulo.");
        }
        return paymentDAO.save(pagamento);
    }

    @Transactional
    public void deletarPagamento(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do pagamento não pode ser nulo.");
        }

        paymentDAO.deleteById(id);
    }

    public List<PaymentModel> listarTodosOsPagamentos() {
        return paymentDAO.findAll();
    }

    public PaymentModel buscarPagamentoPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do pagamento não pode ser nulo.");
        }

        return paymentDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com o ID: " + id));
    }

    public PaymentModel buscarPagamentoPorPedidoId(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
        }

        return paymentDAO.findByOrderId(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado para o pedido com o ID: " + orderId));
    }

    public List<PaymentModel> buscarPagamentosPorMetodoPagamento(String metodoPagamento) {
        if (metodoPagamento == null || metodoPagamento.trim().isEmpty()) {
            throw new IllegalArgumentException("O método de pagamento não pode ser nulo ou vazio.");
        }

        return paymentDAO.findByMetodoPagamento(metodoPagamento);
    }

    public List<PaymentModel> buscarPagamentosPorStatusPagamento(String statusPagamento) {
        if (statusPagamento == null || statusPagamento.trim().isEmpty()) {
            throw new IllegalArgumentException("O status do pagamento não pode ser nulo ou vazio.");
        }

        return paymentDAO.findByStatusPagamento(statusPagamento);
    }
}