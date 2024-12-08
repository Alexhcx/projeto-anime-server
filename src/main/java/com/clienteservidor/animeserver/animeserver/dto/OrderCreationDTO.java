package com.clienteservidor.animeserver.animeserver.dto;

public record OrderCreationDTO(
        Long userId,
        String status,
        String valorTotal,
        String metodoEnvio,
        String custoEnvio,
        String numeroRastreamento
) {}
