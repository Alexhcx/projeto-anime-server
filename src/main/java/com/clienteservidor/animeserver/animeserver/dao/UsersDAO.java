package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clienteservidor.animeserver.animeserver.models.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<UserModel, Long> {

    List<UserModel> findByNome(String nome);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByCpf(String cpf);
}