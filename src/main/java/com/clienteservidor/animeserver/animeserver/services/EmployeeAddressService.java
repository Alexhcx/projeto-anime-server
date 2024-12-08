package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.animeserver.animeserver.dao.EmployeeAddressDAO;
import com.clienteservidor.animeserver.animeserver.dao.EmployeeDAO;
import com.clienteservidor.animeserver.animeserver.models.EmployeeAddressModel;
import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EmployeeAddressService {

  @Autowired
  private EmployeeAddressDAO employeeAddressDAO;

  @Autowired
  private EmployeeDAO employeeDAO;

  @Transactional
  public EmployeeAddressModel cadastrarEnderecoFuncionario(EmployeeAddressModel endereco) { // Método adicionado
    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }
    if (endereco.getEmployee() == null || endereco.getEmployee().getId() == null) {
      throw new IllegalArgumentException("O funcionário associado ao endereço não pode ser nulo.");
    }

    return employeeAddressDAO.save(endereco);
  }

  @Transactional
  public EmployeeAddressModel criarEnderecoFuncionario(Long employeeId, EmployeeAddressModel endereco) {
    if (employeeId == null) {
      throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
    }
    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }

    EmployeeModel employee = employeeDAO.findById(employeeId)
        .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o ID: " + employeeId));

    endereco.setEmployee(employee);

    return employeeAddressDAO.save(endereco);
  }

  @Transactional
  public EmployeeAddressModel atualizarEnderecoFuncionario(EmployeeAddressModel endereco) {
    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }
    if (endereco.getId() == null) {
      throw new IllegalArgumentException("O ID do endereço não pode ser nulo.");
    }

    return employeeAddressDAO.save(endereco);
  }

  @Transactional
  public void deletarEnderecoFuncionario(Long enderecoId) {
    if (enderecoId == null) {
      throw new IllegalArgumentException("O ID do endereço não pode ser nulo.");
    }

    employeeAddressDAO.deleteById(enderecoId);
  }

  public List<EmployeeAddressModel> listarEnderecosDoFuncionario(Long employeeId) {
    if (employeeId == null) {
      throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
    }

    return employeeAddressDAO.findByEmployeeId(employeeId).stream().toList();
  }

  public List<EmployeeAddressModel> listarEnderecosPorCep(String cep) {
    return employeeAddressDAO.findByCep(cep);
  }

  public List<EmployeeAddressModel> listarEnderecosPorCidade(String cidade) {
    return employeeAddressDAO.findByCidade(cidade);
  }

  public List<EmployeeAddressModel> listarEnderecosPorEstado(String estado) {
    return employeeAddressDAO.findByEstado(estado);
  }
}
