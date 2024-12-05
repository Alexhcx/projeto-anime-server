package com.clienteservidor.animeserver.animeserver.dto;

public record ProductOrderDTO(Long id, Long qtdProduto) {

  public Long getId() {
    return id;
  }

  public Long getQtdProdutos() {
    return qtdProduto;
  }

}
