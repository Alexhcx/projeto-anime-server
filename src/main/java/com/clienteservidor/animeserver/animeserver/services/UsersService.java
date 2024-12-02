package com.clienteservidor.animeserver.animeserver.services;

import com.clienteservidor.animeserver.animeserver.dao.userdao.UsersDAO;
import com.clienteservidor.animeserver.animeserver.models.UserModel;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

  @Autowired
  private UsersDAO usersDAO;

  @Transactional
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
    if (usersDAO.findByCPF(user.getCpf()).isPresent()) {
      throw new IllegalArgumentException("Já existe um usuário com este CPF.");
    }
    return usersDAO.save(user);
  }

  public UserModel buscarUsuarioPorId(Long id) {
    // Validações
    if (id == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }

    Optional<UserModel> user = usersDAO.findById(id);
    if (user.isEmpty()) {
      throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
    }

    return user.get();
  }

  @Transactional
  public UserModel atualizarInfoUsuario(Long id, UserModel user) {
    // Validações
    if (id == null) {
      throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
    }
    if (user == null) {
      throw new IllegalArgumentException("Os dados do usuário não podem ser nulos.");
    }

    Optional<UserModel> existingUser = usersDAO.findById(id);
    if (existingUser.isEmpty()) {
      throw new IllegalArgumentException("Usuário não encontrado com o ID: " + id);
    }

    // Atualizar os dados do usuário existente
    UserModel userToUpdate = existingUser.get();
    userToUpdate.setNome(user.getNome());
    userToUpdate.setEmail(user.getEmail());
    userToUpdate.setCpf(user.getCpf());
    userToUpdate.setTelefone(user.getTelefone());
    userToUpdate.setPassword(user.getPassword());
    userToUpdate.setSexo(user.getSexo());
    userToUpdate.setDataNascimento(user.getDataNascimento());

    return usersDAO.update(userToUpdate);
  }

  @Transactional
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
