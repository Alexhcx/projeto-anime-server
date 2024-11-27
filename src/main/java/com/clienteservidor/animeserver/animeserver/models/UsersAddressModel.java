package com.clienteservidor.animeserver.animeserver.models;

import java.io.Serializable;

import com.clienteservidor.animeserver.animeserver.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "address")
public class UsersAddressModel extends Auditable implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserModel user;

  @Column(nullable = false)
  private String cep;

  @Column(nullable = false)
  private String logradouro;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String cidade;

  @Column(nullable = false)
  private String estado;

  @Column(nullable = false)
  private String numero;

  @Column(nullable = false)
  private String complemento;

  @Column(nullable = false)
  private String referencia;

}
