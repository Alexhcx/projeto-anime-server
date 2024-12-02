package com.clienteservidor.animeserver.animeserver.dao.productdao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.ProductModel;

public interface ProductDAO {

  // Create
  ProductModel save(ProductModel product);

  // Read
  Optional<ProductModel> findById(Long id);

  List<ProductModel> findBySupplier(String supplier);

  List<ProductModel> findByPreco(String preco);

  List<ProductModel> findAll();

  // Update
  ProductModel update(ProductModel user);

  // Delete
  void deleteById(Long id);
}
