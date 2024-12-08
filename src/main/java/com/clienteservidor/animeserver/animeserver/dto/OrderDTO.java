package com.clienteservidor.animeserver.animeserver.dto;

import java.util.List;

public record OrderDTO(
        Long userId,
        String status,
        String valorTotal,
        String metodoEnvio,
        String custoEnvio,
        String numeroRastreamento,
        List<ProductOrderDTO> produtos,
        String metodoPagamento,
        String statusPagamento
) {
}
