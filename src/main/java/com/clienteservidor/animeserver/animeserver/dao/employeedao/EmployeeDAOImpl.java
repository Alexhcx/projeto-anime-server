package com.clienteservidor.animeserver.animeserver.dao.employeedao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EmployeeModel save(EmployeeModel employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Optional<EmployeeModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(EmployeeModel.class, id));
    }

    @Override
    public Optional<EmployeeModel> findByName(String nome) {
        return entityManager.createQuery("SELECT e FROM EmployeeModel e WHERE e.nome = :nome", EmployeeModel.class)
                .setParameter("nome", nome)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<EmployeeModel> findByEmail(String email) {
        return entityManager.createQuery("SELECT e FROM EmployeeModel e WHERE e.email = :email", EmployeeModel.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<EmployeeModel> findByCPF(String cpf) {
        return entityManager.createQuery("SELECT e FROM EmployeeModel e WHERE e.cpf = :cpf", EmployeeModel.class)
                .setParameter("cpf", cpf)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<EmployeeModel> findByFuncao(String funcao) {
        return entityManager.createQuery("SELECT e FROM EmployeeModel e WHERE e.funcao = :funcao", EmployeeModel.class)
                .setParameter("funcao", funcao)
                .getResultList();
    }

    @Override
    public List<EmployeeModel> findBySalario(String salario) {
        return entityManager.createQuery("SELECT e FROM EmployeeModel e WHERE e.salario = :salario", EmployeeModel.class)
                .setParameter("salario", salario)
                .getResultList();
    }

    @Override
    public List<EmployeeModel> findByDataNascimento(String dataNascimento) {
        return entityManager.createQuery("SELECT e FROM EmployeeModel e WHERE e.dataNascimento = :dataNascimento", EmployeeModel.class)
                .setParameter("dataNascimento", dataNascimento)
                .getResultList();
    }

    @Override
    public List<EmployeeModel> findAll() {
        return entityManager.createQuery("SELECT e FROM EmployeeModel e", EmployeeModel.class).getResultList();
    }

    @Override
    public EmployeeModel update(EmployeeModel employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(Long id) {
        EmployeeModel employee = entityManager.getReference(EmployeeModel.class, id);
        entityManager.remove(employee);
    }
}
