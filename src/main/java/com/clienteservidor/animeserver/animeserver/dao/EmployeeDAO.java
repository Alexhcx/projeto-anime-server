package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeeModel, Long> {

  Optional<EmployeeModel> findByNome(String nome);

  Optional<EmployeeModel> findByEmail(String email);

  Optional<EmployeeModel> findByCpf(String cpf);

  List<EmployeeModel> findByFuncao(String funcao);

  List<EmployeeModel> findBySalario(String salario);

  List<EmployeeModel> findByDataNascimento(String dataNascimento);
}
