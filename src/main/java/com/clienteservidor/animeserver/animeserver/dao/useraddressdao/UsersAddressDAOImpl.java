package com.clienteservidor.animeserver.animeserver.dao.useraddressdao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.UsersAddressModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsersAddressDAOImpl implements UsersAddressDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public UsersAddressModel save(UsersAddressModel userAddress) {
    entityManager.persist(userAddress);
    return userAddress;
  }

  @Override
  public Optional<UsersAddressModel> findById(Long id) {
    return Optional.ofNullable(entityManager.find(UsersAddressModel.class, id));
  }

  @Override
  public List<UsersAddressModel> findByUserId(Long userId) {
    return entityManager.createQuery(
        "SELECT ua FROM UsersAddressModel ua WHERE ua.user.id = :userId", UsersAddressModel.class)
        .setParameter("userId", userId)
        .getResultList(); // Usar getResultList() para retornar todos os resultados
  }

  @Override
  public List<UsersAddressModel> findByCep(String cep) {
    return entityManager.createQuery("SELECT u FROM UsersAddressModel u WHERE u.cep = :cep", UsersAddressModel.class)
        .setParameter("cep", cep)
        .getResultList();
  }

  @Override
  public List<UsersAddressModel> findByCidade(String cidade) {
    return entityManager
        .createQuery("SELECT u FROM UsersAddressModel u WHERE u.cidade = :cidade", UsersAddressModel.class)
        .setParameter("cidade", cidade)
        .getResultList();
  }

  @Override
  public List<UsersAddressModel> findByEstado(String estado) {
    return entityManager
        .createQuery("SELECT u FROM UsersAddressModel u WHERE u.estado = :estado", UsersAddressModel.class)
        .setParameter("estado", estado)
        .getResultList();
  }

  @Override
  public List<UsersAddressModel> findAll() {
    return entityManager.createQuery("SELECT u FROM UsersAdressModel u", UsersAddressModel.class).getResultList();
  }

  @Override
  public UsersAddressModel update(UsersAddressModel userAddress) {
    return entityManager.merge(userAddress);
  }

  @Override
  public void deleteById(Long id) {
    Optional<UsersAddressModel> userOptional = findById(id);
    userOptional.ifPresent(entityManager::remove);
  }

}
