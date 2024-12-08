package com.clienteservidor.animeserver.animeserver.models;

import java.io.Serializable;

import com.clienteservidor.animeserver.animeserver.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "employee_address")
public class EmployeeAddressModel extends Auditable implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private EmployeeModel employee;

  private String cep;

  private String logradouro;

  private String bairro;

  private String cidade;

  private String estado;

  private String numero;

  private String complemento;

  private String referencia;
}
