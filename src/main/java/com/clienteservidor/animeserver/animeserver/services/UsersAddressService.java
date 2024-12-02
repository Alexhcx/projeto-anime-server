package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.animeserver.animeserver.dao.useraddressdao.UsersAddressDAO;
import com.clienteservidor.animeserver.animeserver.dao.userdao.UsersDAO;
import com.clienteservidor.animeserver.animeserver.models.UserModel;
import com.clienteservidor.animeserver.animeserver.models.UsersAddressModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsersAddressService {

  @Autowired
  private UsersAddressDAO usersAddressDAO;

  @Autowired
  private UsersDAO usersDAO; // Para acessar os dados do usuário

  @Transactional
  public UsersAddressModel criarEndereco(Long userId, UsersAddressModel endereco) {
    // Validações
    if (userId == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }
    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }
    // ... outras validações (campos obrigatórios, formato do CEP, etc.) ...

    // Buscar o usuário
    Optional<UserModel> userOptional = usersDAO.findById(userId);
    if (userOptional.isEmpty()) {
      throw new EntityNotFoundException("Usuário não encontrado com o ID: " + userId);
    }
    UserModel user = userOptional.get();

    // Associar o endereço ao usuário
    endereco.setUser(user);

    return usersAddressDAO.save(endereco);
  }

  @Transactional
  public UsersAddressModel atualizarEndereco(Long enderecoId, UsersAddressModel endereco) {
    // Validações
    if (enderecoId == null) {
      throw new IllegalArgumentException("O ID do endereço não pode ser nulo.");
    }
    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }
    // ... outras validações ...

    Optional<UsersAddressModel> existingAddress = usersAddressDAO.findById(enderecoId);
    if (existingAddress.isEmpty()) {
      throw new EntityNotFoundException("Endereço não encontrado com o ID: " + enderecoId);
    }

    // Atualizar os dados do endereço existente
    UsersAddressModel addressToUpdate = existingAddress.get();
    addressToUpdate.setBairro(endereco.getBairro());
    addressToUpdate.setCep(endereco.getCep());
    addressToUpdate.setCidade(endereco.getCidade());
    addressToUpdate.setComplemento(endereco.getComplemento());
    addressToUpdate.setEstado(endereco.getEstado());
    addressToUpdate.setLogradouro(endereco.getLogradouro());
    addressToUpdate.setNumero(endereco.getNumero());
    addressToUpdate.setReferencia(endereco.getReferencia());

    return usersAddressDAO.update(addressToUpdate);
  }

  @Transactional
  public void deletarEndereco(Long enderecoId) {
    // Validações
    if (enderecoId == null) {
      throw new IllegalArgumentException("O ID do endereço não pode ser nulo.");
    }

    usersAddressDAO.deleteById(enderecoId);
  }

  public List<UsersAddressModel> listarEnderecosDoUsuario(Long userId) {
    // Validações
    if (userId == null) {
        throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }

    return usersAddressDAO.findByUserId(userId); // Agora retorna uma lista diretamente
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
