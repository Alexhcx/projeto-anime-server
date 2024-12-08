package com.clienteservidor.animeserver.animeserver.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.clienteservidor.animeserver.animeserver.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "suppliers")
public class SupplierModel extends Auditable implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
  private Set<ProductModel> products = new HashSet<>();

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, unique=true)
  private String cnpj;

  @Column(nullable = false, unique=true)
  private String email;

  private String telefone;

  private String cep;

  private String logradouro;

  private String bairro;

  private String cidade;

  private String estado;

  private String numero;

  private String complemento;

  @Override
  public String toString() {
      return this.nome;
  }

}
