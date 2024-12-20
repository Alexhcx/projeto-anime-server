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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name = "products")
public class ProductModel extends Auditable implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
  private Set<OrdersModel> orders = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private SupplierModel supplier;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String descricao;

  @Column(nullable = false)
  private String preco;

  @Column(nullable = false)
  private String qtdEstoque;

  @Column(nullable = true, columnDefinition = "TEXT[]")
  private String[] imagens;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductModel{");
        sb.append("id=").append(id);
        sb.append(", orders=").append(orders);
        sb.append(", supplier=").append(supplier);
        sb.append(", nome=").append(nome);
        sb.append(", descricao=").append(descricao);
        sb.append(", preco=").append(preco);
        sb.append(", qtdEstoque=").append(qtdEstoque);
        sb.append(", imagens=").append(imagens);
        sb.append('}');
        return sb.toString();
    }


}
