package br.com.estoque.api.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "es_produto")
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Long id;
	
	@Column(length = 70,nullable = false,name = "nome_produto")
	private String nome;
	
	@Column(nullable = false,name = "preco_produto")
	private Double preco;
	
	@Column(nullable = false,name = "qtd_produto")
	private Integer quantidade;
	
	@Column(nullable = false,name = "id_fabricante")
	private Long idFabricante;
	
	@Column(nullable = false,name = "id_modelo")
	private Long idModelo;

}
