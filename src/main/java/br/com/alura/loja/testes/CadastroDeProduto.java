package br.com.alura.loja.testes;

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
		
		em.close();
	}

	private static void cadastrarProduto() {
		Categoria categoria = Categoria.builder().nome("Celulares").build();
		Produto p = Produto.builder().categoria(categoria).descricao("Cellular Xioami").nome("Mi Note 9").build();
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(p);
		em.flush();
	}
}
