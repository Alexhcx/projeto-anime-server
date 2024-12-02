package com.clienteservidor.animeserver.animeserver.services;

import com.clienteservidor.animeserver.animeserver.dao.employeeaddressdao.EmployeeAddressDAO;
import com.clienteservidor.animeserver.animeserver.dao.employeedao.EmployeeDAO;
import com.clienteservidor.animeserver.animeserver.models.EmployeeAddressModel;
import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeAddressService {

  @Autowired
  private EmployeeAddressDAO employeeAddressDAO;

  @Autowired
  private EmployeeDAO employeeDAO;

  @Transactional
  public EmployeeAddressModel criarEnderecoFuncionario(Long employeeId, EmployeeAddressModel endereco) {
    if (employeeId == null) {
      throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
    }
    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }

    Optional<EmployeeModel> employeeOptional = employeeDAO.findById(employeeId);
    if (employeeOptional.isEmpty()) {
      throw new EntityNotFoundException("Funcionário não encontrado com o ID: " + employeeId);
    }
    EmployeeModel employee = employeeOptional.get();

    endereco.setEmployee(employee);

    return employeeAddressDAO.save(endereco);
  }

  @Transactional
  public EmployeeAddressModel atualizarEnderecoFuncionario(Long enderecoId, EmployeeAddressModel endereco) {
    if (enderecoId == null) {
      throw new IllegalArgumentException("O ID do endereço não pode ser nulo.");
    }
    if (endereco == null) {
      throw new IllegalArgumentException("O endereço não pode ser nulo.");
    }

    Optional<EmployeeAddressModel> existingAddress = employeeAddressDAO.findById(enderecoId);
    if (existingAddress.isEmpty()) {
      throw new EntityNotFoundException("Endereço não encontrado com o ID: " + enderecoId);
    }

    EmployeeAddressModel addressToUpdate = existingAddress.get();
    addressToUpdate.setCep(endereco.getCep());
    addressToUpdate.setLogradouro(endereco.getLogradouro());
    addressToUpdate.setNumero(endereco.getNumero());
    addressToUpdate.setComplemento(endereco.getComplemento());
    addressToUpdate.setBairro(endereco.getBairro());
    addressToUpdate.setCidade(endereco.getCidade());
    addressToUpdate.setEstado(endereco.getEstado());
    addressToUpdate.setReferencia(endereco.getReferencia());

    return employeeAddressDAO.update(addressToUpdate);
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

    return employeeAddressDAO.findByUserId(employeeId).stream().toList();
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
