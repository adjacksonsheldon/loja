package br.com.alura.loja.dao;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CategoriaDaoTest {

	private EntityManager em = JPAUtil.getEntityManager();
	
	ProdutoDao produtoDao = new ProdutoDao(em);

	@Test
	public void deveriaIncluirUmaCategoriaComIdUm() {
		em.getTransaction().begin();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		Categoria categoria = Categoria.builder().nome("Celular").build();
		categoriaDao.cadastrar(categoria);
		
		em.getTransaction().commit();
		em.close();
		
		assertTrue(categoria.getId() == 1);
	}

//	@Test
//	public void deveriaIncluirUmaCategoriaComIdUm() {
//		em.getTransaction().begin();
//		
//		Produto p = Produto.builder()
//				.nome("Mi Note 9")
//				.descricao("Cellular Xioami")
//				.categoria(categoria)
//				.preco(new BigDecimal("9.90"))
//				.build();
//		
//		produtoDao.cadastrar(p);
//		em.getTransaction().commit();
//		em.close();
//		
//		assertTrue(categoria.getId() == 1);
//	}

}
