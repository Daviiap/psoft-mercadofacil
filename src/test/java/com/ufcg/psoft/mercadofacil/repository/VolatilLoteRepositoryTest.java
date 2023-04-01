package com.ufcg.psoft.mercadofacil.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;

@SpringBootTest
class VolatilLoteRepositoryTest {

	@Autowired
	LoteRepository<Lote, Long> driver;

	Lote lote;
	Lote resultado;
	Produto produto;

	@BeforeEach
	void setup() {
		produto = Produto.builder().id(1L).nome("Produto Base").codigoBarra("123456789").fabricante("Fabricante Base")
				.preco(125.36).build();
		lote = Lote.builder().id(1L).numeroDeItens(100).produto(produto).build();
	}

	@AfterEach
	void tearDown() {
		produto = null;
		driver.deleteAll();
	}

	@Test
	@DisplayName("Adicionar o primeiro Lote no repositorio de dados")
	void salvarPrimeiroLote() {
		resultado = driver.save(lote);

		assertEquals(driver.findAll().size(), 1);
		assertEquals(resultado.getId().longValue(), lote.getId().longValue());
		assertEquals(resultado.getProduto(), produto);
	}

	@Test
	@DisplayName("Adicionar o segundo Lote (ou posterior) no repositorio de dados")
	void salvarSegundoLoteOuPosterior() {
		Produto produtoExtra = Produto.builder().id(2L).nome("Produto Extra").codigoBarra("987654321")
				.fabricante("Fabricante Extra").preco(125.36).build();
		Lote loteExtra = Lote.builder().id(2L).numeroDeItens(100).produto(produtoExtra).build();
		driver.save(lote);

		resultado = driver.save(loteExtra);

		assertEquals(driver.findAll().size(), 2);
		assertEquals(resultado.getId().longValue(), loteExtra.getId().longValue());
		assertEquals(resultado.getProduto(), produtoExtra);

	}

	@Test
	@DisplayName("Buscar um lote existente pelo id")
	void buscarLoteExistentePeloID() {
		Produto produto = Produto.builder().id(1L).nome("Produto Extra").codigoBarra("987654321")
				.fabricante("Fabricante Extra").preco(125.36).build();
		Lote lote = Lote.builder().id(1L).numeroDeItens(100).produto(produto).build();
		driver.save(lote);

		resultado = driver.find(1L);
		assertNotNull(resultado);
		assertEquals(lote.getId().longValue(), resultado.getId().longValue());
	}

	@Test
	@DisplayName("Buscar um lote não existente pelo id")
	void buscarLoteNaoExistentePeloID() {
		Produto produto = Produto.builder().id(1L).nome("Produto Extra").codigoBarra("987654321")
				.fabricante("Fabricante Extra").preco(125.36).build();
		Lote lote = Lote.builder().id(1L).numeroDeItens(100).produto(produto).build();
		driver.save(lote);

		resultado = driver.find(2L);
		assertNull(resultado);
	}

	@Test
	@DisplayName("Buscar todos os lotes")
	void buscarTodosOsLotes() {
		Produto produto = Produto.builder().id(1L).nome("Produto Extra").codigoBarra("987654321")
				.fabricante("Fabricante Extra").preco(125.36).build();
		Lote lote = Lote.builder().id(1L).numeroDeItens(100).produto(produto).build();
		Lote lote2 = Lote.builder().id(2L).numeroDeItens(100).produto(produto).build();
		Lote lote3 = Lote.builder().id(3L).numeroDeItens(100).produto(produto).build();
		driver.save(lote);
		driver.save(lote2);
		driver.save(lote3);

		List<Lote> resultado = driver.findAll();
		assertEquals(3, resultado.size());
	}

	@Test
	@DisplayName("Atualizar lote existente")
	void atualizarLoteExistente() {
		Produto produto = Produto.builder().id(1L).nome("Produto Extra").codigoBarra("987654321")
				.fabricante("Fabricante Extra").preco(125.36).build();
		Lote lote = Lote.builder().id(1L).numeroDeItens(100).produto(produto).build();
		driver.save(lote);

		lote.setNumeroDeItens(110);

		resultado = driver.update(lote);
		assertEquals(110, resultado.getNumeroDeItens());
	}

	@Test
	@DisplayName("Atualizar lote não existente")
	void atualizarLoteNaoExistente() {
		Produto produto = Produto.builder().id(1L).nome("Produto Extra").codigoBarra("987654321")
				.fabricante("Fabricante Extra").preco(125.36).build();
		Lote lote = Lote.builder().id(1L).numeroDeItens(100).produto(produto).build();

		lote.setNumeroDeItens(110);

		resultado = driver.update(lote);
		assertNull(resultado);
	}

	@Test
	@DisplayName("Deletar lote")
	void deletarLote() {
		Produto produto = Produto.builder().id(1L).nome("Produto Extra").codigoBarra("987654321")
				.fabricante("Fabricante Extra").preco(125.36).build();
		Lote lote = Lote.builder().id(1L).numeroDeItens(100).produto(produto).build();
		Lote lote2 = Lote.builder().id(2L).numeroDeItens(100).produto(produto).build();
		Lote lote3 = Lote.builder().id(3L).numeroDeItens(100).produto(produto).build();
		driver.save(lote);
		driver.save(lote2);
		driver.save(lote3);

		driver.delete(lote);

		assertEquals(2, driver.findAll().size());
	}
}
