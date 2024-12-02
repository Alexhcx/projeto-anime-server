package com.clienteservidor.animeserver.animeserver.dao.suppliersdao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.ProductModel;
import com.clienteservidor.animeserver.animeserver.models.SupplierModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SuppliersDAOImpl implements SuppliersDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public SupplierModel save(SupplierModel supplier) {
    entityManager.persist(supplier);
    return supplier;
  }

  @Override
  public Optional<SupplierModel> findById(Long id) {
    return Optional.ofNullable(entityManager.find(SupplierModel.class, id));
  }

  @Override
  public Optional<SupplierModel> findByCnpj(String cnpj) {
    return entityManager.createQuery("SELECT s FROM SupplierModel s WHERE s.cnpj = :cnpj", SupplierModel.class)
        .setParameter("cnpj", cnpj)
        .getResultStream()
        .findFirst();
  }

  @Override
  public List<SupplierModel> findByNome(String nome) {
    return entityManager.createQuery("SELECT s FROM SupplierModel s WHERE s.nome = :nome", SupplierModel.class)
        .setParameter("nome", nome)
        .getResultList();
  }

  @Override
  public List<SupplierModel> findByCidade(String cidade) {
    return entityManager.createQuery("SELECT s FROM SupplierModel s WHERE s.cidade = :cidade", SupplierModel.class)
        .setParameter("cidade", cidade)
        .getResultList();
  }

  @Override
  public List<SupplierModel> findByEstado(String estado) {
    return entityManager.createQuery("SELECT s FROM SupplierModel s WHERE s.estado = :estado", SupplierModel.class)
        .setParameter("estado", estado)
        .getResultList();
  }

  @Override
  public List<SupplierModel> findAll() {
    return entityManager.createQuery("SELECT s FROM SupplierModel s", SupplierModel.class).getResultList();
  }

  @Override
  public List<ProductModel> findProductsBySupplierId(Long supplierId) {
    return entityManager
        .createQuery("SELECT p FROM ProductModel p WHERE p.supplier.id = :supplierId", ProductModel.class)
        .setParameter("supplierId", supplierId)
        .getResultList();
  }

  @Override
  public SupplierModel update(SupplierModel supplier) {
    return entityManager.merge(supplier);
  }

  @Override
  public void deleteById(Long id) {
    SupplierModel supplier = entityManager.getReference(SupplierModel.class, id);
    entityManager.remove(supplier);
  }
}
