package com.clienteservidor.animeserver.animeserver.dao.useraddressdao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.UsersAddressModel;

public interface UsersAddressDAO  {
    // Create
    UsersAddressModel save(UsersAddressModel userAddress);

    // Read
    Optional<UsersAddressModel> findById(Long id);
    List<UsersAddressModel> findByUserId(Long userId);
    List<UsersAddressModel> findByCep(String cep);
    List<UsersAddressModel> findByCidade(String cidade);
    List<UsersAddressModel> findByEstado(String estado);
    List<UsersAddressModel> findAll();

    // Update
    UsersAddressModel update(UsersAddressModel userAddress);

    // Delete
    void deleteById(Long id);
}
