package com.ufcg.psoft.mercadofacil.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cliente {

	private Long id;

	private Long cpf;

	private String nome;

	private Integer idade;

	private String endereco;

	public Long getId() {
		return id;
	}

	public Long getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
