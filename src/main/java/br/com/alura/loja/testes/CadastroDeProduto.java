package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	private static EntityManager em = JPAUtil.getEntityManager();
	private static CategoriaDao categoriaDao = new CategoriaDao(em);
	private static ProdutoDao produtoDao = new ProdutoDao(em);

	public static void main(String[] args) {
		em.getTransaction().begin();
		
		cadastrarProduto();
		
		produtoDao.buscarPorNomeDaCategoria("Celulares").forEach(System.out::println);		
		System.out.println(produtoDao.buscarPrecoDoProdutoComNome("Mi Note 9"));
		
		em.close();
	}

	private static void cadastrarProduto() {
		Categoria categoria = Categoria.builder().nome("Celulares").build();
		
		Produto p = Produto.builder()
				.nome("Mi Note 9")
				.descricao("Cellular Xioami")
				.categoria(categoria)
				.preco(new BigDecimal("9.90"))
				.build();
		
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(p);
		em.flush();
	}
}
