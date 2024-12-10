package com.clienteservidor.animeserver.animeserver.dto;

public record UserEnderecoDTO(
    String nome,
    String cpf,
    String sexo,
    String email,
    String password,
    String dataNascimento,
    String telefone,
    String cep,
    String logradouro,
    String numero,
    String bairro,
    String cidade,
    String estado,
    String complemento,
    String referencia
) { }