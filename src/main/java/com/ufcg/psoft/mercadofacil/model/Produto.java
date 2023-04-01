package com.ufcg.psoft.mercadofacil.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Produto {

	private Long id;

	private String nome;

	private double preco;

	private String codigoBarra;

	private String fabricante;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
