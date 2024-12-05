package com.clienteservidor.animeserver.animeserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.animeserver.animeserver.dao.SuppliersDAO;
import com.clienteservidor.animeserver.animeserver.models.SupplierModel;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SupplierService {
  @Autowired
  private SuppliersDAO suppliersDAO;

  @Transactional
  public SupplierModel cadastrarFornecedor(SupplierModel fornecedor) {
    // Validações
    if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) {
      throw new IllegalArgumentException("O nome do fornecedor não pode ser vazio.");
    }
    if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().isEmpty()) {
      throw new IllegalArgumentException("O CNPJ do fornecedor não pode ser vazio.");
    }

    return suppliersDAO.save(fornecedor);
  }

  @Transactional
  public SupplierModel atualizarFornecedor(SupplierModel fornecedor) {
    // Validações
    if (fornecedor == null) {
      throw new IllegalArgumentException("Os dados do fornecedor não podem ser nulos.");
    }
    if (fornecedor.getId() == null) {
      throw new IllegalArgumentException("O ID do fornecedor não pode ser nulo.");
    }

    return suppliersDAO.save(fornecedor);
  }

  @Transactional
  public void deletarFornecedor(Long id) {
    // Validações
    if (id == null) {
      throw new IllegalArgumentException("O ID do fornecedor não pode ser nulo.");
    }

    suppliersDAO.deleteById(id);
  }

  public List<SupplierModel> mostrarTodosOsFornecedores() {
    return suppliersDAO.findAll();
  }

  public SupplierModel buscarFornecedorPorId(Long id) {
    // Validações
    if (id == null) {
      throw new IllegalArgumentException("O ID do fornecedor não pode ser nulo.");
    }

    return suppliersDAO.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com o ID: " + id));
  }

  public Optional<SupplierModel> buscarFornecedorPorCnpj(String cnpj) {
    return suppliersDAO.findByCnpj(cnpj);
  }

  public List<SupplierModel> buscarFornecedorPorNome(String nome) {
    return suppliersDAO.findByNome(nome);
  }

  public List<SupplierModel> buscarFornecedorPorCidade(String cidade) {
    return suppliersDAO.findByCidade(cidade);
  }

  public List<SupplierModel> buscarFornecedorPorEstado(String estado) {
    return suppliersDAO.findByEstado(estado);
  }
}
