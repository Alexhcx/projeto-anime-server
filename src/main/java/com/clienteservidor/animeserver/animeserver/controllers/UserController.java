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

import com.clienteservidor.animeserver.animeserver.dto.LoginResponseDTO;
import com.clienteservidor.animeserver.animeserver.dto.UserEnderecoDTO;
import com.clienteservidor.animeserver.animeserver.dto.UserLoginDTO;
import com.clienteservidor.animeserver.animeserver.models.UserModel;
import com.clienteservidor.animeserver.animeserver.models.UsersAddressModel;
import com.clienteservidor.animeserver.animeserver.services.UsersAddressService;
import com.clienteservidor.animeserver.animeserver.services.UsersService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("anime/api/users")
public class UserController {

    @Autowired
    private UsersService userService;

    @Autowired
    private UsersAddressService usersAddressService;

    @Transactional
    @PostMapping("/endereco/cadastrar")
    public ResponseEntity<?> cadastrarUsuarioComEndereco(@RequestBody UserEnderecoDTO userEnderecoDTO) {
        try {
            UsersAddressModel endereco = new UsersAddressModel();
            endereco.setCep(userEnderecoDTO.cep());
            endereco.setLogradouro(userEnderecoDTO.logradouro());
            endereco.setNumero(userEnderecoDTO.numero());
            endereco.setBairro(userEnderecoDTO.bairro());
            endereco.setCidade(userEnderecoDTO.cidade());
            endereco.setEstado(userEnderecoDTO.estado());
            endereco.setComplemento(userEnderecoDTO.complemento());
            endereco.setReferencia(userEnderecoDTO.referencia());

            UserModel user = new UserModel();
            user.setNome(userEnderecoDTO.nome());
            user.setCpf(userEnderecoDTO.cpf());
            user.setSexo(userEnderecoDTO.sexo());
            user.setEmail(userEnderecoDTO.email());
            user.setPassword(userEnderecoDTO.password());
            user.setDataNascimento(userEnderecoDTO.dataNascimento());
            user.setTelefone(userEnderecoDTO.telefone());
            UserModel createdUser = userService.cadastrarUsuario(user);

            endereco.setUser(createdUser);
            createdUser.setEndereco(endereco);

            UsersAddressModel enderecoCriado = usersAddressService.criarEndereco(endereco);

            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<UserModel> cadastrarUsuario(@RequestBody UserModel user) {
        try {
            UserModel createdUser = userService.cadastrarUsuario(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginDTO loginDTO) {
        try {
            String email = loginDTO.email();
            String password = loginDTO.password();

            UserModel user = userService.buscarUsuarioPorEmail(email);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if (!userService.verificarSenha(user, password)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            LoginResponseDTO loginResponse = new LoginResponseDTO(user.getId(), user.getNome());

            return new ResponseEntity<>(loginResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> buscarUsuarioPorId(@PathVariable Long id) {
        try {
            UserModel user = userService.buscarUsuarioPorId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> atualizarInfoUsuario(@PathVariable Long id, @RequestBody UserModel user) {
        try {
            user.setId(id);
            UserModel updatedUser = userService.atualizarInfoUsuario(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        try {
            userService.deletarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<UserModel> mostrarTodosOsUsuarios() {
        return userService.mostrarTodosOsUsuarios();
    }
}
