package com.clienteservidor.animeserver.animeserver.dao;

import com.clienteservidor.animeserver.animeserver.models.SupplierModel;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersDAO extends JpaRepository<SupplierModel, Long> {

    Optional<SupplierModel> findByCnpj(String cnpj);

    List<SupplierModel> findByNome(String nome);

    List<SupplierModel> findByCidade(String cidade);

    List<SupplierModel> findByEstado(String estado);
}
