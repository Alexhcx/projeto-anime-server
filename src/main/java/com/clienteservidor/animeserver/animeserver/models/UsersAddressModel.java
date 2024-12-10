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
@Table(name = "address")
public class UsersAddressModel extends Auditable implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserModel user;

  private String cep;

  private String logradouro;

  private String bairro;

  private String cidade;

  private String estado;

  private String numero;

  private String complemento;

  private String referencia;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UsersAddressModel{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", cep=").append(cep);
        sb.append(", logradouro=").append(logradouro);
        sb.append(", bairro=").append(bairro);
        sb.append(", cidade=").append(cidade);
        sb.append(", estado=").append(estado);
        sb.append(", numero=").append(numero);
        sb.append(", complemento=").append(complemento);
        sb.append(", referencia=").append(referencia);
        sb.append('}');
        return sb.toString();
    }

}
