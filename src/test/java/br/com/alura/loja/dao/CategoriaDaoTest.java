package br.com.alura.loja.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDaoTest {
	
	@Test
	public void deveriaIncluirUmaCategoriaComIdUm() {
		
		CategoriaDao categoriaDao = new CategoriaDao();
		Categoria categoria = Categoria.builder().nome("Celular").build();
		categoriaDao.cadastrar(categoria);

		assertTrue(categoria.getId() == 1);
	}

	@Test
	public void deveriaIncluirLoteDeCategoria() {
		CategoriaDao categoriaDao = new CategoriaDao();
		List<Categoria> categorias = List.of(
				Categoria.builder().nome("Celular").build()
			   ,Categoria.builder().nome("Tv").build()
			   ,Categoria.builder().nome("Notebook").build()
			   ,Categoria.builder().nome("Carros").build()
			   ,Categoria.builder().nome("Avião").build()
			   ,Categoria.builder().nome("Casas").build());
		
		categoriaDao.cadastrar(categorias);
		
		assertTrue(categorias.get(0).getId() == 1);
	}

}
