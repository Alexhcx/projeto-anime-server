package com.clienteservidor.animeserver.animeserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clienteservidor.animeserver.animeserver.models.EmployeeAddressModel;
import com.clienteservidor.animeserver.animeserver.services.EmployeeAddressService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/employeeAddresses") // Defina o caminho base para os endpoints de endereços de funcionários
public class EmployeeAddressController {

  @Autowired
  private EmployeeAddressService employeeAddressService;

  @PostMapping("/{employeeId}")
  public ResponseEntity<EmployeeAddressModel> criarEnderecoFuncionario(@PathVariable Long employeeId,
      @RequestBody EmployeeAddressModel endereco) {
    try {
      EmployeeAddressModel createdAddress = employeeAddressService.criarEnderecoFuncionario(employeeId, endereco);
      return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    } catch (IllegalArgumentException | EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{enderecoId}")
  public ResponseEntity<EmployeeAddressModel> atualizarEnderecoFuncionario(@PathVariable Long enderecoId,
      @RequestBody EmployeeAddressModel endereco) {
    try {
      endereco.setId(enderecoId); // Definir o ID do endereço
      EmployeeAddressModel updatedAddress = employeeAddressService.atualizarEnderecoFuncionario(endereco);
      return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    } catch (IllegalArgumentException | EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{enderecoId}")
  public ResponseEntity<Void> deletarEnderecoFuncionario(@PathVariable Long enderecoId) {
    try {
      employeeAddressService.deletarEnderecoFuncionario(enderecoId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/employee/{employeeId}")
  public List<EmployeeAddressModel> listarEnderecosDoFuncionario(@PathVariable Long employeeId) {
    return employeeAddressService.listarEnderecosDoFuncionario(employeeId);
  }

  @GetMapping("/cep/{cep}")
  public List<EmployeeAddressModel> listarEnderecosPorCep(@PathVariable String cep) {
    return employeeAddressService.listarEnderecosPorCep(cep);
  }

  @GetMapping("/cidade/{cidade}")
  public List<EmployeeAddressModel> listarEnderecosPorCidade(@PathVariable String cidade) {
    return employeeAddressService.listarEnderecosPorCidade(cidade);
  }

  @GetMapping("/estado/{estado}")
  public List<EmployeeAddressModel> listarEnderecosPorEstado(@PathVariable String estado) {
    return employeeAddressService.listarEnderecosPorEstado(estado);
  }
}
