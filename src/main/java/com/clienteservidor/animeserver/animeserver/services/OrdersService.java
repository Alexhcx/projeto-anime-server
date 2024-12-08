package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clienteservidor.animeserver.animeserver.dao.OrdersDAO;
import com.clienteservidor.animeserver.animeserver.dao.OrdersProductsDAO;
import com.clienteservidor.animeserver.animeserver.dao.PaymentDAO;
import com.clienteservidor.animeserver.animeserver.dto.OrderDTO;
import com.clienteservidor.animeserver.animeserver.dto.ProductOrderDTO;
import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.OrdersProductsModel;
import com.clienteservidor.animeserver.animeserver.models.PaymentModel;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdersService {

  @Autowired
  private OrdersDAO ordersDAO;

  @Autowired
  private UsersService usersService;

  @Autowired
  private OrdersProductsService ordersProductsService;

  @Autowired
  private OrdersProductsDAO ordersProductsDAO;

  @Autowired
  private PaymentDAO paymentDAO;

  @Transactional
  public OrdersModel criarPedido(OrderDTO orderDTO) {
    // Criar o pedido
    OrdersModel pedido = new OrdersModel();
    pedido.setUser(usersService.buscarUsuarioPorId(orderDTO.userId()));
    pedido.setStatus(orderDTO.status());
    pedido.setValorTotal(orderDTO.valorTotal());
    pedido.setMetodoEnvio(orderDTO.metodoEnvio());
    pedido.setCustoEnvio(orderDTO.custoEnvio());
    pedido.setNumeroRastreamento(orderDTO.numeroRastreamento());

    OrdersModel savedOrder = ordersDAO.save(pedido);

    for (ProductOrderDTO produtoDTO : orderDTO.produtos()) {
      ordersProductsService.adicionarProdutoAoPedido(
          savedOrder.getId(),
          produtoDTO.id(),
          produtoDTO.qtdProdutos());
    }

    PaymentModel pagamento = new PaymentModel();
    pagamento.setMetodoPagamento(orderDTO.metodoPagamento());
    pagamento.setStatusPagamento(orderDTO.statusPagamento());
    pagamento.setOrder(savedOrder);
    paymentDAO.save(pagamento);

    return savedOrder;
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

  @Transactional
  public void adicionarProdutosAoPedido(Long orderId, List<ProductOrderDTO> produtos) {
    if (orderId == null) {
      throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
    }
    if (produtos == null || produtos.isEmpty()) {
      throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
    }

    for (ProductOrderDTO produto : produtos) {
      ordersProductsService.adicionarProdutoAoPedido(orderId, produto.id(), produto.qtdProdutos());
    }
  }

  @Transactional
  public void removerProdutosDoPedido(Long orderId, List<ProductOrderDTO> produtos) {
    if (orderId == null) {
      throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
    }
    if (produtos == null || produtos.isEmpty()) {
      throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
    }

    for (ProductOrderDTO produtoDTO : produtos) {
      Long productId = produtoDTO.id();
      Long qtdRemover = produtoDTO.qtdProdutos();

      OrdersProductsModel orderProduct = ordersProductsDAO.findByOrderIdAndProductId(orderId, productId)
          .orElseThrow(() -> new EntityNotFoundException(
              "Associação entre pedido e produto não encontrada para orderId=" + orderId +
                  " e productId=" + productId));

      Long qtdAtual = orderProduct.getQtdProduto();

      if (qtdRemover >= qtdAtual) {
        ordersProductsDAO.delete(orderProduct);
      } else {
        orderProduct.setQtdProduto(qtdAtual - qtdRemover);
        ordersProductsDAO.save(orderProduct);
      }
    }
  }
}
