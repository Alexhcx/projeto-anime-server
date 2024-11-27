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
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users")
public class UserModel extends Auditable implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<UsersAddressModel> address = new HashSet<>();

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<OrdersModel> orders= new HashSet<>();

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, unique = true)
  private String cpf;

  @Column(nullable = false)
  private String sexo;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String dataNascimento;

  private String telefone;

}
