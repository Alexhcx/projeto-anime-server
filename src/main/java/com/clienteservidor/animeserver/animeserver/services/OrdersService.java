package com.clienteservidor.animeserver.animeserver.services;

import com.clienteservidor.animeserver.animeserver.dao.OrdersDAO;
import com.clienteservidor.animeserver.animeserver.dao.PaymentDAO;
import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.PaymentModel;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

  @Autowired
  private OrdersDAO ordersDAO;

  @Autowired
  private PaymentDAO paymentDAO;

  @Transactional
  public OrdersModel criarPedidoComPagamento(OrdersModel pedido, PaymentModel pagamento) {

    OrdersModel savedOrder = ordersDAO.save(pedido);

    pagamento.setOrder(savedOrder);
    paymentDAO.save(pagamento);

    return savedOrder;
  }

  @Transactional
  public void adicionarProdutosAoPedido(Long orderId, List<ProductModel> produtos) {

    if (orderId == null) {
      throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
    }
    if (produtos == null || produtos.isEmpty()) {
      throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
    }

    for (ProductModel produto : produtos) {
      ordersDAO.addProductToOrder(orderId, produto.getId());
    }
  }

  @Transactional
  public void removerProdutosDoPedido(Long orderId, List<Long> productIds) {

    if (orderId == null) {
      throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
    }
    if (productIds == null || productIds.isEmpty()) {
      throw new IllegalArgumentException("A lista de IDs de produtos não pode ser nula ou vazia.");
    }

    for (Long productId : productIds) {
      ordersDAO.removeProductFromOrder(orderId, productId);
    }
  }

  public OrdersModel buscarPedidoPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
    }

    Optional<OrdersModel> pedidoOptional = ordersDAO.findById(id);
    if (pedidoOptional.isEmpty()) {
      throw new EntityNotFoundException("Pedido não encontrado com o ID: " + id);
    }

    return pedidoOptional.get();
  }

  public List<OrdersModel> buscarPedidosPorUsuarioId(Long userId) {
    if (userId == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }

    return ordersDAO.findByUserId(userId);
  }

  public List<OrdersModel> buscarPedidosPorStatus(String status) {
    if (status == null || status.trim().isEmpty()) {
      throw new IllegalArgumentException("O status do pedido não pode ser nulo ou vazio.");
    }

    return ordersDAO.findByStatus(status);
  }

  public List<ProductModel> buscarProdutosDoPedido(Long orderId) {
    if (orderId == null) {
      throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
    }

    return ordersDAO.findProductsByOrderId(orderId);
  }

  public List<OrdersModel> listarTodosOsPedidos() {
    return ordersDAO.findAll();
  }

  @Transactional
  public OrdersModel atualizarPedido(OrdersModel pedido) {
    if (pedido == null) {
      throw new IllegalArgumentException("Os dados do pedido não podem ser nulos.");
    }
    if (pedido.getId() == null) {
      throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
    }

    return ordersDAO.save(pedido);
  }

  @Transactional
  public void deletarPedido(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
    }

    ordersDAO.deleteById(id);
  }
}
