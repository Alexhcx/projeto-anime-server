package com.clienteservidor.animeserver.animeserver.dao.employeedao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;


public interface EmployeeDAO {

      // Create
    EmployeeModel save(EmployeeModel employee);

    // Read
    Optional<EmployeeModel> findById(Long id);
    Optional<EmployeeModel> findByName(String nome);
    Optional<EmployeeModel> findByEmail(String email);
    Optional<EmployeeModel> findByCPF(String cpf);
    List<EmployeeModel> findByFuncao(String funcao);
    List<EmployeeModel> findBySalario(String salario);
    List<EmployeeModel> findByDataNascimento(String dataNascimento);
    List<EmployeeModel> findAll();

    // Update
    EmployeeModel update(EmployeeModel user);

    // Delete
    void deleteById(Long id);
}
