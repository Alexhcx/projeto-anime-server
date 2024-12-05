package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.animeserver.animeserver.dao.UsersAddressDAO;
import com.clienteservidor.animeserver.animeserver.dao.UsersDAO;
import com.clienteservidor.animeserver.animeserver.models.UserModel;
import com.clienteservidor.animeserver.animeserver.models.UsersAddressModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsersAddressService {

  @Autowired
  private UsersAddressDAO usersAddressDAO;

  @Autowired
  private UsersDAO usersDAO;

  @Transactional
  public UsersAddressModel criarEndereco(Long userId, UsersAddressModel endereco) {

    if (userId == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }
    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }
    UserModel user = usersDAO.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + userId));

    endereco.setUser(user);

    return usersAddressDAO.save(endereco);
  }

  @Transactional
  public UsersAddressModel atualizarEndereco(UsersAddressModel endereco) {

    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }
    if (endereco.getId() == null) {
      throw new IllegalArgumentException("O ID do endereço não pode ser nulo.");
    }

    return usersAddressDAO.save(endereco); 
  }

  @Transactional
  public void deletarEndereco(Long enderecoId) {
    if (enderecoId == null) {
      throw new IllegalArgumentException("O ID do endereço não pode ser nulo.");
    }

    usersAddressDAO.deleteById(enderecoId);
  }

  public List<UsersAddressModel> listarEnderecosDoUsuario(Long userId) {
    if (userId == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }

    return usersAddressDAO.findByUserId(userId);
  }

  public List<UsersAddressModel> listarEnderecosPorCep(String cep) {
    return usersAddressDAO.findByCep(cep);
  }

  public List<UsersAddressModel> listarEnderecosPorCidade(String cidade) {
    return usersAddressDAO.findByCidade(cidade);
  }

  public List<UsersAddressModel> listarEnderecosPorEstado(String estado) {
    return usersAddressDAO.findByEstado(estado);
  }
}
