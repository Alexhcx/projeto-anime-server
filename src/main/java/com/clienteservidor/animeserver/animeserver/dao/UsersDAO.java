package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.UserModel;

public interface UsersDAO {

    // Create
    UserModel save(UserModel user);

    // Read
    Optional<UserModel> findById(Long id);
    Optional<UserModel> findByName(String name);
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByCPF(String CPF);
    List<UserModel> findAll();

    // Update
    UserModel update(UserModel user);

    // Delete
    void deleteById(Long id);
}