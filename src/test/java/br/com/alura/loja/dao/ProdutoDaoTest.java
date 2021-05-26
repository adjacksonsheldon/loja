package br.com.alura.loja.dao;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class ProdutoDaoTest {
	
	private EntityManager em = JPAUtil.getEntityManager();
	private ProdutoDao produtoDao;
	
	private Categoria categoria1;
	
	@Before
	public void setup() {
		em.getTransaction().begin();
		produtoDao = new ProdutoDao(em);
		
		cadastrarCategoria();
	}

	private void cadastrarCategoria() {
	
		CategoriaDao categoriaDao = new CategoriaDao(em);
		Categoria categoria = Categoria.builder().nome("Celular").build();
		categoriaDao.cadastrar(categoria);		
		em.getTransaction().commit();
	}
	
	@Test
	public void deveriaIncluirUmProdutoComIdUm() {
		em.getTransaction().begin();
		
		Produto produto = Produto.builder()
				.nome("Mi Note 9")
				.descricao("Celular Xioami")
				.categoria(categoria1)
				.preco(new BigDecimal("9.90"))
				.build();
		
		produtoDao.cadastrar(produto);
		
		em.getTransaction().commit();
		em.close();
		
		assertTrue(produto.getId() == 1);
	}
}