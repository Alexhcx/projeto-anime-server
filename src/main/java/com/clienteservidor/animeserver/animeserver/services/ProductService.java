package com.clienteservidor.animeserver.animeserver.services;

import com.clienteservidor.animeserver.animeserver.dao.ProductDAO;
import com.clienteservidor.animeserver.animeserver.dao.SuppliersDAO;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;
import com.clienteservidor.animeserver.animeserver.models.SupplierModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductDAO productDAO;

  @Autowired
  private SuppliersDAO suppliersDAO; // Adicione o SuppliersDAO

  @Transactional
  public ProductModel cadastrarProduto(ProductModel produto, Long fornecedorId) {
    // Validações
    if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
      throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
    }
    if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
      throw new IllegalArgumentException("A descrição do produto não pode ser vazia.");
    }
    if (produto.getPreco() == null || produto.getPreco().trim().isEmpty()) {
      throw new IllegalArgumentException("O preço do produto não pode ser vazio.");
    }
    if (fornecedorId == null) {
      throw new IllegalArgumentException("O ID do fornecedor não pode ser nulo.");
    }

    // Buscar o fornecedor pelo ID
    SupplierModel fornecedor = suppliersDAO.findById(fornecedorId)
        .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com o ID: " + fornecedorId));

    // Atribuir o fornecedor ao produto
    produto.setSupplier(fornecedor);

    return productDAO.save(produto);
  }

  @Transactional
  public ProductModel atualizarProduto(ProductModel produto) {
    // Validações
    if (produto == null) {
      throw new IllegalArgumentException("Os dados do produto não podem ser nulos.");
    }
    if (produto.getId() == null) {
      throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
    }

    return productDAO.save(produto);
  }

  @Transactional
  public void deletarProduto(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
    }

    productDAO.deleteById(id);
  }

  public List<ProductModel> mostrarTodosOsProdutos() {
    return productDAO.findAll();
  }

  public ProductModel buscarProdutoPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
    }

    return productDAO.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));
  }
}
