package com.clienteservidor.animeserver.animeserver.dao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.EmployeeAddressModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAddressDAO extends JpaRepository<EmployeeAddressModel, Long> {

  Optional<EmployeeAddressModel> findByEmployeeId(Long employeeId);

  List<EmployeeAddressModel> findByCep(String cep);

  List<EmployeeAddressModel> findByCidade(String cidade);

  List<EmployeeAddressModel> findByEstado(String estado);
}
