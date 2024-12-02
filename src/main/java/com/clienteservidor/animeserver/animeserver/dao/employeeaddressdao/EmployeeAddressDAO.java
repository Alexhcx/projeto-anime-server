package com.clienteservidor.animeserver.animeserver.dao.employeeaddressdao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.EmployeeAddressModel;

public interface EmployeeAddressDAO {
  // Create
  EmployeeAddressModel save(EmployeeAddressModel employeeAddress);

  // Read
  Optional<EmployeeAddressModel> findById(Long id);

  Optional<EmployeeAddressModel> findByUserId(Long userId);

  List<EmployeeAddressModel> findByCep(String cep);

  List<EmployeeAddressModel> findByCidade(String cidade);

  List<EmployeeAddressModel> findByEstado(String estado);

  List<EmployeeAddressModel> findAll();

  // Update
  EmployeeAddressModel update(EmployeeAddressModel employeeAddress);

  // Delete
  void deleteById(Long id);
}
