package com.clienteservidor.animeserver.animeserver.dto;

public record UpdatePaymentDTO(
        String metodoPagamento,
        String statusPagamento
) {}
