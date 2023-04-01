package com.ufcg.psoft.mercadofacil.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lote {

	private Long id;

	private Produto produto;

	private int numeroDeItens;

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getNumeroDeItens() {
		return numeroDeItens;
	}

	public void setNumeroDeItens(int numeroDeItens) {
		this.numeroDeItens = numeroDeItens;
	}

}
