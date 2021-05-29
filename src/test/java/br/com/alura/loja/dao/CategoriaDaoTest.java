package br.com.alura.loja.dao;

import java.util.List;

import org.junit.Test;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;

public class CategoriaDaoTest {
	
	@Test
	public void deveriaIncluirUmaCategoriaComIdUm() {
		
		CategoriaDao categoriaDao = new CategoriaDao();
		Categoria categoria = Categoria.builder().categoriaId(CategoriaId.builder().nome("Celular").tipo("XYZ").build()).build();
		categoriaDao.cadastrar(categoria);

	}

	@Test
	public void deveriaIncluirLoteDeCategoria() {
		CategoriaDao categoriaDao = new CategoriaDao();
		List<Categoria> categorias = List.of(
				Categoria.builder().categoriaId(CategoriaId.builder().nome("Tv").tipo("XYZ").build()).build()
			   ,Categoria.builder().categoriaId(CategoriaId.builder().nome("Notebook").tipo("XYZ").build()).build()
			   ,Categoria.builder().categoriaId(CategoriaId.builder().nome("Carros").tipo("XYZ").build()).build()
			   ,Categoria.builder().categoriaId(CategoriaId.builder().nome("Avião").tipo("XYZ").build()).build()
			   ,Categoria.builder().categoriaId(CategoriaId.builder().nome("Casas").tipo("XYZ").build()).build());
		
		categoriaDao.cadastrar(categorias);
		
	}

}
