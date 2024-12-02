package com.clienteservidor.animeserver.animeserver.dao.productdao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.ProductModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductDAOImpl implements ProductDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ProductModel save(ProductModel product) {
    entityManager.persist(product);
    return product;
  }

  @Override
  public Optional<ProductModel> findById(Long id) {
    return Optional.ofNullable(entityManager.find(ProductModel.class, id));
  }

  @Override
  public List<ProductModel> findBySupplier(String supplier) {
    return entityManager
        .createQuery("SELECT p FROM ProductModel p WHERE p.supplier.name = :supplier", ProductModel.class)
        .setParameter("supplier", supplier)
        .getResultList(); // Alterado para retornar uma lista
  }

  @Override
  public List<ProductModel> findByPreco(String preco) {
    return entityManager.createQuery("SELECT p FROM ProductModel p WHERE p.price = :preco", ProductModel.class)
        .setParameter("preco", preco)
        .getResultList(); // Alterado para retornar uma lista
  }

  @Override
  public List<ProductModel> findAll() {
    return entityManager.createQuery("SELECT p FROM ProductModel p", ProductModel.class).getResultList();
  }

  @Override
  public ProductModel update(ProductModel product) {
    return entityManager.merge(product);
  }

  @Override
  public void deleteById(Long id) {
    ProductModel product = entityManager.getReference(ProductModel.class, id);
    entityManager.remove(product);
  }
}
