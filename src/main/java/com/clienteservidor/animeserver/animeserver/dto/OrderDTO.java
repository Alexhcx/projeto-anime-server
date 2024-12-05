package com.clienteservidor.animeserver.animeserver.dto;

import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.PaymentModel;

public record OrderDTO(OrdersModel order, PaymentModel payment) {

}
