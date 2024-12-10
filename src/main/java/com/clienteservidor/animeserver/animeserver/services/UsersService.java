package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

    String senhaCriptografada = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    user.setPassword(senhaCriptografada);

    return usersDAO.save(user);
  }

  public void atualizarSenhas() {
    List<UserModel> usuarios = usersDAO.findAll();
    for (UserModel usuario : usuarios) {
      String senhaPlana = usuario.getPassword();
      String senhaCriptografada = BCrypt.hashpw(senhaPlana, BCrypt.gensalt());
      usuario.setPassword(senhaCriptografada);
      usersDAO.save(usuario);
    }
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

  public UserModel buscarUsuarioPorEmail(String email) {
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("O email do usuário não pode ser vazio.");
    }
    return usersDAO.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o email: " + email));
  }

  public boolean verificarSenha(UserModel user, String password) {
    if (user == null) {
      throw new IllegalArgumentException("O usuário não pode ser nulo.");
    }
    if (password == null || password.trim().isEmpty()) {
      throw new IllegalArgumentException("A senha não pode ser vazia.");
    }

    String senhaCriptografada = user.getPassword(); // Assumindo que a senha é armazenada como hash no banco de dados

    return BCrypt.checkpw(password, senhaCriptografada);
  }
}