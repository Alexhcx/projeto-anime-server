package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.animeserver.animeserver.dao.UsersDAO;
import com.clienteservidor.animeserver.animeserver.models.UserModel;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsersService {

  @Autowired
  private UsersDAO usersDAO;

  public UserModel cadastrarUsuario(UserModel user) {
    // Validações
    if (user.getNome() == null || user.getNome().trim().isEmpty()) {
      throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
    }
    if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
      throw new IllegalArgumentException("O email do usuário não pode ser vazio.");
    }
    if (user.getCpf() == null || user.getCpf().trim().isEmpty()) {
      throw new IllegalArgumentException("O CPF do usuário não pode ser vazio.");
    }
    if (usersDAO.findByEmail(user.getEmail()).isPresent()) {
      throw new IllegalArgumentException("Já existe um usuário com este email.");
    }
    if (usersDAO.findByCpf(user.getCpf()).isPresent()) {
      throw new IllegalArgumentException("Já existe um usuário com este CPF.");
    }
    return usersDAO.save(user);
  }

  public UserModel buscarUsuarioPorId(Long id) {
    // Validações
    if (id == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }

    return usersDAO.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
  }

  public UserModel atualizarInfoUsuario(UserModel user) {
    // Validações
    if (user == null) {
      throw new IllegalArgumentException("Os dados do usuário não podem ser nulos.");
    }
    if (user.getId() == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }

    return usersDAO.save(user);
  }

  public void deletarUsuario(Long id) {
    // Validações
    if (id == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }

    usersDAO.deleteById(id);
  }

  public List<UserModel> mostrarTodosOsUsuarios() {
    return usersDAO.findAll();
  }
}