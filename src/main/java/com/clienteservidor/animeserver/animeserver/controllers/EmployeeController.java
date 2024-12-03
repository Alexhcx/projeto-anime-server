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

import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;
import com.clienteservidor.animeserver.animeserver.services.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/anime/api/employees") // Defina o caminho base para os endpoints de funcionários
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<EmployeeModel> cadastrarFuncionario(@RequestBody EmployeeModel funcionario) {
    try {
      EmployeeModel createdEmployee = employeeService.cadastrarFuncionario(funcionario);
      return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeModel> atualizarInformacoesFuncionario(@PathVariable Long id,
      @RequestBody EmployeeModel funcionario) {
    try {
      funcionario.setId(id); // Definir o ID do funcionário
      EmployeeModel updatedEmployee = employeeService.atualizarInformacoesFuncionario(funcionario);
      return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
    try {
      employeeService.deletarFuncionario(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  public List<EmployeeModel> mostrarTodosOsFuncionarios() {
    return employeeService.mostrarTodosOsFuncionarios();
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeModel> buscarFuncionarioPorId(@PathVariable Long id) {
    try {
      EmployeeModel employee = employeeService.buscarFuncionarioPorId(id);
      return new ResponseEntity<>(employee, HttpStatus.OK);
    } catch (IllegalArgumentException | EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/funcao/{funcao}")
  public List<EmployeeModel> buscarFuncionarioPorFuncao(@PathVariable String funcao) {
    return employeeService.buscarFuncionarioPorFuncao(funcao);
  }

  @GetMapping("/salario/{salario}")
  public List<EmployeeModel> buscarFuncionarioPorSalario(@PathVariable String salario) {
    return employeeService.buscarFuncionarioPorSalario(salario);
  }

  @GetMapping("/dataNascimento/{dataNascimento}")
  public List<EmployeeModel> buscarFuncionarioPorDataNascimento(@PathVariable String dataNascimento) {
    return employeeService.buscarFuncionarioPorDataNascimento(dataNascimento);
  }
}
