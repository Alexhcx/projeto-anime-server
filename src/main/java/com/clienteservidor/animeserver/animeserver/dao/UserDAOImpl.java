package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.UserModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserDAOImpl implements UsersDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserModel save(UserModel user) {
        entityManager.persist(user);
        return user; 
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserModel.class, id));
    }

    @Override
    public Optional<UserModel> findByName(String name) {
        return entityManager.createQuery("SELECT u FROM UserModel u WHERE u.nome = :nome", UserModel.class)
                .setParameter("nome", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM UserModel u WHERE u.email = :email", UserModel.class)
                .setParameter("email", email)
                .getResultStream() 
                .findFirst();
    }

    @Override
    public Optional<UserModel> findByCPF(String cpf) {
        return entityManager.createQuery("SELECT u FROM UserModel u WHERE u.cpf = :cpf", UserModel.class)
                .setParameter("cpf", cpf)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<UserModel> findAll() {
        return entityManager.createQuery("SELECT u FROM UserModel u", UserModel.class).getResultList();
    }

    @Override
    public UserModel update(UserModel user) {
        return entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        Optional<UserModel> userOptional = findById(id);
        userOptional.ifPresent(entityManager::remove);
    }
}
