package com.clienteservidor.animeserver.animeserver.services;

import com.clienteservidor.animeserver.animeserver.dao.employeedao.EmployeeDAO;
import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeDAO employeeDAO;

  @Transactional
  public EmployeeModel cadastrarFuncionario(EmployeeModel funcionario) {
    if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
      throw new IllegalArgumentException("O nome do funcionário não pode ser vazio.");
    }
    if (funcionario.getEmail() == null || funcionario.getEmail().trim().isEmpty()) {
      throw new IllegalArgumentException("O email do funcionário não pode ser vazio.");
    }
    if (funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty()) {
      throw new IllegalArgumentException("O CPF do funcionário não pode ser vazio.");
    }
    if (employeeDAO.findByEmail(funcionario.getEmail()).isPresent()) {
      throw new IllegalArgumentException("Já existe um funcionário com este email.");
    }
    if (employeeDAO.findByCPF(funcionario.getCpf()).isPresent()) {
      throw new IllegalArgumentException("Já existe um funcionário com este CPF.");
    }

    return employeeDAO.save(funcionario);
  }

  @Transactional
  public EmployeeModel atualizarInformacoesFuncionario(Long id, EmployeeModel funcionario) {
    if (id == null) {
      throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
    }
    if (funcionario == null) {
      throw new IllegalArgumentException("Os dados do funcionário não podem ser nulos.");
    }

    Optional<EmployeeModel> existingEmployee = employeeDAO.findById(id);
    if (existingEmployee.isEmpty()) {
      throw new IllegalArgumentException("Funcionário não encontrado com o ID: " + id);
    }

    EmployeeModel employeeToUpdate = existingEmployee.get();
    employeeToUpdate.setNome(funcionario.getNome());
    employeeToUpdate.setEmail(funcionario.getEmail());
    employeeToUpdate.setCpf(funcionario.getCpf());
    employeeToUpdate.setTelefone(funcionario.getTelefone());
    employeeToUpdate.setPassword(funcionario.getPassword());
    employeeToUpdate.setFuncao(funcionario.getFuncao());
    employeeToUpdate.setSalario(funcionario.getSalario());
    employeeToUpdate.setDataNascimento(funcionario.getDataNascimento());

    return employeeDAO.update(employeeToUpdate);
  }

  @Transactional
  public void deletarFuncionario(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
    }

    employeeDAO.deleteById(id);
  }

  public List<EmployeeModel> mostrarTodosOsFuncionarios() {
    return employeeDAO.findAll();
  }

  public EmployeeModel buscarFuncionarioPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
    }

    Optional<EmployeeModel> funcionario = employeeDAO.findById(id);
    if (funcionario.isEmpty()) {
      throw new EntityNotFoundException("Funcionário não encontrado com o ID: " + id);
    }

    return funcionario.get();
  }

  public List<EmployeeModel> buscarFuncionarioPorFuncao(String funcao) {
    return employeeDAO.findByFuncao(funcao);
  }

  public List<EmployeeModel> buscarFuncionarioPorSalario(String salario) {
    return employeeDAO.findBySalario(salario);
  }

  public List<EmployeeModel> buscarFuncionarioPorDataNascimento(String dataNascimento) {
    return employeeDAO.findByDataNascimento(dataNascimento);
  }
}