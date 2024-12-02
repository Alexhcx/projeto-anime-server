package com.clienteservidor.animeserver.animeserver.services;

import com.clienteservidor.animeserver.animeserver.dao.productdao.ProductDAO;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;
import com.clienteservidor.animeserver.animeserver.models.SupplierModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  @Autowired
  private ProductDAO productDAO;

  @Transactional
  public ProductModel cadastrarProduto(ProductModel produto) {
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

    return productDAO.save(produto);
  }

  @Transactional
  public ProductModel atualizarProduto(Long id, ProductModel produto) {
    // Validações
    if (id == null) {
      throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
    }
    if (produto == null) {
      throw new IllegalArgumentException("Os dados do produto não podem ser nulos.");
    }

    Optional<ProductModel> existingProduct = productDAO.findById(id);
    if (existingProduct.isEmpty()) {
      throw new IllegalArgumentException("Produto não encontrado com o ID: " + id);
    }

    ProductModel productToUpdate = existingProduct.get();
    productToUpdate.setNome(produto.getNome());
    productToUpdate.setDescricao(produto.getDescricao());
    productToUpdate.setPreco(produto.getPreco());
    productToUpdate.setQtdEstoque(produto.getQtdEstoque());
    productToUpdate.setImagens(produto.getImagens());

    return productDAO.update(productToUpdate);
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

    Optional<ProductModel> produto = productDAO.findById(id);
    if (produto.isEmpty()) {
      throw new EntityNotFoundException("Produto não encontrado com o ID: " + id);
    }

    return produto.get();
  }

  public SupplierModel buscarFornecedorDoProduto(Long productId) {
    if (productId == null) {
      throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
    }

    Optional<ProductModel> productOptional = productDAO.findById(productId);
    if (productOptional.isEmpty()) {
      throw new EntityNotFoundException("Produto não encontrado com o ID: " + productId);
    }

    ProductModel product = productOptional.get();
    if (product.getSupplier() == null) {
      throw new EntityNotFoundException("Fornecedor não encontrado para o produto com o ID: " + productId);
    }

    return product.getSupplier();
  }
}
