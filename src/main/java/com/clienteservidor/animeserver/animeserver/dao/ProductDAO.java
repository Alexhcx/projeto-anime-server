package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;

import com.clienteservidor.animeserver.animeserver.models.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByPreco(String preco);
}
