package com.clienteservidor.animeserver.animeserver.dao.employeeaddressdao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.EmployeeAddressModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EmployeeAddressDAOImpl implements EmployeeAddressDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EmployeeAddressModel save(EmployeeAddressModel employeeAddress) {
        entityManager.persist(employeeAddress);
        return employeeAddress;
    }

    @Override
    public Optional<EmployeeAddressModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(EmployeeAddressModel.class, id));
    }

    @Override
    public Optional<EmployeeAddressModel> findByUserId(Long userId) {
        return entityManager.createQuery("SELECT ea FROM EmployeeAddressModel ea WHERE ea.employee.id = :userId", EmployeeAddressModel.class)
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<EmployeeAddressModel> findByCep(String cep) {
        return entityManager.createQuery("SELECT ea FROM EmployeeAddressModel ea WHERE ea.cep = :cep", EmployeeAddressModel.class)
                .setParameter("cep", cep)
                .getResultList();
    }

    @Override
    public List<EmployeeAddressModel> findByCidade(String cidade) {
        return entityManager.createQuery("SELECT ea FROM EmployeeAddressModel ea WHERE ea.cidade = :cidade", EmployeeAddressModel.class)
                .setParameter("cidade", cidade)
                .getResultList();
    }

    @Override
    public List<EmployeeAddressModel> findByEstado(String estado) {
        return entityManager.createQuery("SELECT ea FROM EmployeeAddressModel ea WHERE ea.estado = :estado", EmployeeAddressModel.class)
                .setParameter("estado", estado)
                .getResultList();
    }

    @Override
    public List<EmployeeAddressModel> findAll() {
        return entityManager.createQuery("SELECT ea FROM EmployeeAddressModel ea", EmployeeAddressModel.class)
                .getResultList();
    }

    @Override
    public EmployeeAddressModel update(EmployeeAddressModel employeeAddress) {
        return entityManager.merge(employeeAddress);
    }

    @Override
    public void deleteById(Long id) {
        EmployeeAddressModel employeeAddress = entityManager.getReference(EmployeeAddressModel.class, id);
        entityManager.remove(employeeAddress);
    }
}
