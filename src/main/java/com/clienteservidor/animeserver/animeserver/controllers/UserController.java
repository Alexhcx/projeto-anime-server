package com.clienteservidor.animeserver.animeserver.controllers;

import com.clienteservidor.animeserver.animeserver.models.UserModel;
import com.clienteservidor.animeserver.animeserver.services.UsersService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("anime/api/users")
public class UserController {

    @Autowired
    private UsersService userService;

    @PostMapping("/post")
    public ResponseEntity<UserModel> cadastrarUsuario(@RequestBody UserModel user) {
        try {
            UserModel createdUser = userService.cadastrarUsuario(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserModel> buscarUsuarioPorId(@PathVariable Long id) {
        try {
            UserModel user = userService.buscarUsuarioPorId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<UserModel> atualizarInfoUsuario(@PathVariable Long id, @RequestBody UserModel user) {
        try {
            UserModel updatedUser = userService.atualizarInfoUsuario(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        try {
            userService.deletarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public List<UserModel> mostrarTodosOsUsuarios() {
        return userService.mostrarTodosOsUsuarios();
    }
}
