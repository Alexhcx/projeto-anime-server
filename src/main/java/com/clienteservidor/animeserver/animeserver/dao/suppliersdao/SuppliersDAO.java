package com.clienteservidor.animeserver.animeserver.dao.suppliersdao;

import com.clienteservidor.animeserver.animeserver.models.ProductModel;
import com.clienteservidor.animeserver.animeserver.models.SupplierModel;
import java.util.List;
import java.util.Optional;

public interface SuppliersDAO {

    // Create
    SupplierModel save(SupplierModel supplier);

    // Read
    Optional<SupplierModel> findById(Long id);
    Optional<SupplierModel> findByCnpj(String cnpj); // Busca por CNPJ
    List<SupplierModel> findByNome(String nome); // Busca por nome (pode haver mais de um com o mesmo nome)
    List<SupplierModel> findByCidade(String cidade); // Busca por cidade
    List<SupplierModel> findByEstado(String estado); // Busca por estado
    List<SupplierModel> findAll();
    List<ProductModel> findProductsBySupplierId(Long supplierId);

    // Update
    SupplierModel update(SupplierModel supplier);

    // Delete
    void deleteById(Long id);
}
