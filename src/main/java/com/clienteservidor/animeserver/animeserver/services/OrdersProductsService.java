package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.animeserver.animeserver.dao.OrdersDAO;
import com.clienteservidor.animeserver.animeserver.dao.OrdersProductsDAO;
import com.clienteservidor.animeserver.animeserver.dao.ProductDAO;
import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.OrdersProductsModel;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrdersProductsService {

    @Autowired
    private OrdersProductsDAO ordersProductsDAO;

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public OrdersProductsModel adicionarProdutoAoPedido(Long orderId, Long productId, Long qtdProduto) {
        if (orderId == null) {
            throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
        }
        if (productId == null) {
            throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
        }
        if (qtdProduto == null || qtdProduto <= 0) {
            throw new IllegalArgumentException("A quantidade de produto deve ser maior que zero.");
        }

        OrdersModel pedido = ordersDAO.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + orderId));

        ProductModel produto = productDAO.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + productId));

        OrdersProductsModel ordersProducts = new OrdersProductsModel();
        ordersProducts.setOrder(pedido);
        ordersProducts.setProduct(produto);
        ordersProducts.setQtdProduto(qtdProduto);

        return ordersProductsDAO.save(ordersProducts);
    }

    @Transactional
    public void removerProdutoDoPedido(Long orderId, Long productId) {
        if (orderId == null) {
            throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
        }
        if (productId == null) {
            throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
        }

        ordersProductsDAO.deleteByOrderIdAndProductId(orderId, productId);
    }

    public List<OrdersProductsModel> listarProdutosDoPedido(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
        }

        return ordersProductsDAO.findByOrderId(orderId);
    }

    @Transactional
    public OrdersProductsModel atualizarProdutoNoPedido(Long orderId, Long productId, Long novaQtd) {
        if (orderId == null) {
            throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
        }
        if (productId == null) {
            throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
        }
        if (novaQtd == null || novaQtd <= 0) {
            throw new IllegalArgumentException("A nova quantidade de produto deve ser maior que zero.");
        }

        OrdersProductsModel ordersProducts = ordersProductsDAO.findByOrderIdAndProductId(orderId, productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Associação entre pedido e produto não encontrada para orderId=" + orderId +
                                " e productId=" + productId));

        ordersProducts.setQtdProduto(novaQtd);

        return ordersProductsDAO.save(ordersProducts);
    }

    public OrdersProductsModel buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do pedido do produto não pode ser nulo.");
        }

        return ordersProductsDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido do produto não encontrado com o ID: " + id));
    }

    public List<OrdersProductsModel> listarTodosOsPedidosProdutos() {
        return ordersProductsDAO.findAll();
    }
}
