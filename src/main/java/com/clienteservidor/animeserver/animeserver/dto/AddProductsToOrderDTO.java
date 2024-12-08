package com.clienteservidor.animeserver.animeserver.dto;

import java.util.List;

public record AddProductsToOrderDTO(
        List<ProductOrderDTO> produtos
) {}
