package com.clienteservidor.animeserver.animeserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clienteservidor.animeserver.animeserver.models.UsersAddressModel;
import com.clienteservidor.animeserver.animeserver.services.UsersAddressService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("anime/api/addresses")
public class UsersAddressController {

    @Autowired
    private UsersAddressService userAddressService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<UsersAddressModel> criarEndereco(@PathVariable Long userId,
            @RequestBody UsersAddressModel endereco) {
        try {
            UsersAddressModel createdAddress = userAddressService.criarEndereco(userId, endereco);
            return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{enderecoId}")
    public ResponseEntity<UsersAddressModel> atualizarEndereco(@PathVariable Long enderecoId,
            @RequestBody UsersAddressModel endereco) {
        try {
            endereco.setId(enderecoId);
            UsersAddressModel updatedAddress = userAddressService.atualizarEndereco(endereco);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{enderecoId}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long enderecoId) {
        try {
            userAddressService.deletarEndereco(enderecoId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}")
    public List<UsersAddressModel> listarEnderecosDoUsuario(@PathVariable Long userId) {
        return userAddressService.listarEnderecosDoUsuario(userId);
    }

    @GetMapping("/cep/{cep}")
    public List<UsersAddressModel> listarEnderecosPorCep(@PathVariable String cep) {
        return userAddressService.listarEnderecosPorCep(cep);
    }

    @GetMapping("/cidade/{cidade}")
    public List<UsersAddressModel> listarEnderecosPorCidade(@PathVariable String cidade) {
        return userAddressService.listarEnderecosPorCidade(cidade);
    }

    @GetMapping("/estado/{estado}")
    public List<UsersAddressModel> listarEnderecosPorEstado(@PathVariable String estado) {
        return userAddressService.listarEnderecosPorEstado(estado);
    }
}
