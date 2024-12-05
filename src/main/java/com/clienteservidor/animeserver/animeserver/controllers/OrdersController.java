package com.clienteservidor.animeserver.animeserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clienteservidor.animeserver.animeserver.dto.OrderDTO;
import com.clienteservidor.animeserver.animeserver.dto.ProductOrderDTO;
import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.PaymentModel;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;
import com.clienteservidor.animeserver.animeserver.services.OrdersService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/anime/api/pedidos")
public class OrdersController {

  @Autowired
  private OrdersService orderService;

  @PostMapping
  public ResponseEntity<OrdersModel> criarPedidoComPagamento(@RequestBody OrderDTO orderDTO) {
    try {
      OrdersModel pedido = orderDTO.order();
      PaymentModel pagamento = orderDTO.payment();
      OrdersModel createdOrder = orderService.criarPedidoComPagamento(pedido, pagamento);
      return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrdersModel> atualizarPedido(@PathVariable Long id, @RequestBody OrdersModel pedido) {
    try {
      pedido.setId(id);
      OrdersModel updatedOrder = orderService.atualizarPedido(pedido);
      return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
    try {
      orderService.deletarPedido(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  public List<OrdersModel> listarTodosOsPedidos() {
    return orderService.listarTodosOsPedidos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrdersModel> buscarPedidoPorId(@PathVariable Long id) {
    try {
      OrdersModel order = orderService.buscarPedidoPorId(id);
      return new ResponseEntity<>(order, HttpStatus.OK);
    } catch (IllegalArgumentException | EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/usuario/{userId}")
  public List<OrdersModel> buscarPedidosPorUsuarioId(@PathVariable Long userId) {
    return orderService.buscarPedidosPorUsuarioId(userId);
  }

  @GetMapping("/status/{status}")
  public List<OrdersModel> buscarPedidosPorStatus(@PathVariable String status) {
    return orderService.buscarPedidosPorStatus(status);
  }

  @GetMapping("/{orderId}/produtos")
  public List<ProductModel> buscarProdutosDoPedido(@PathVariable Long orderId) {
    return orderService.buscarProdutosDoPedido(orderId);
  }

  @PostMapping("/{orderId}/produtos")
  public ResponseEntity<Void> adicionarProdutosAoPedido(@PathVariable Long orderId,
      @RequestBody List<ProductOrderDTO> produtos) { // Receber List<ProductOrderDTO>
    try {
      orderService.adicionarProdutosAoPedido(orderId, produtos);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
  }

  @DeleteMapping("/{orderId}/produtos")
  public ResponseEntity<Void> removerProdutosDoPedido(@PathVariable Long orderId,
      @RequestBody List<ProductOrderDTO> produtos) {
    try {
      orderService.removerProdutosDoPedido(orderId, produtos);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
