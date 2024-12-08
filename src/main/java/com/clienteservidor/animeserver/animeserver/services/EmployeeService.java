package com.clienteservidor.animeserver.animeserver.services;

import com.clienteservidor.animeserver.animeserver.dao.EmployeeDAO;
import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public EmployeeModel cadastrarFuncionario(EmployeeModel funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do funcionário não pode ser vazio.");
        }
        if (funcionario.getEmail() == null || funcionario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O email do funcionário não pode ser vazio.");
        }
        if (funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do funcionário não pode ser vazio.");
        }
        if (employeeDAO.findByEmail(funcionario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um funcionário com este email.");
        }
        if (employeeDAO.findByCpf(funcionario.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um funcionário com este CPF.");
        }

        String senhaCriptografada = BCrypt.hashpw(funcionario.getPassword(), BCrypt.gensalt());
        funcionario.setPassword(senhaCriptografada);

        return employeeDAO.save(funcionario);
    }

    public EmployeeModel buscarFuncionarioPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
        }

        return employeeDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o ID: " + id));
    }

    @Transactional
    public EmployeeModel atualizarInformacoesFuncionario(EmployeeModel funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Os dados do funcionário não podem ser nulos.");
        }
        if (funcionario.getId() == null) {
            throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
        }

        return employeeDAO.save(funcionario);
    }

    @Transactional
    public void deletarFuncionario(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do funcionário não pode ser nulo.");
        }

        employeeDAO.deleteById(id);
    }

    public List<EmployeeModel> mostrarTodosOsFuncionarios() {
        return employeeDAO.findAll();
    }

    public List<EmployeeModel> buscarFuncionarioPorFuncao(String funcao) {
        return employeeDAO.findByFuncao(funcao);
    }

    public List<EmployeeModel> buscarFuncionarioPorSalario(String salario) {
        return employeeDAO.findBySalario(salario);
    }

    public List<EmployeeModel> buscarFuncionarioPorDataNascimento(String dataNascimento) {
        return employeeDAO.findByDataNascimento(dataNascimento);
    }

    public Optional<EmployeeModel> findByEmail(String email) {
        return employeeDAO.findByEmail(email);
    }
}