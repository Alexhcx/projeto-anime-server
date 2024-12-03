package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;

import com.clienteservidor.animeserver.animeserver.models.UsersAddressModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAddressDAO extends JpaRepository<UsersAddressModel, Long> {

    List<UsersAddressModel> findByUserId(Long userId);

    List<UsersAddressModel> findByCep(String cep);

    List<UsersAddressModel> findByCidade(String cidade);

    List<UsersAddressModel> findByEstado(String estado);
}
