package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

public class TestesHelper {

	private CategoriaDao categoriaDao;
	private ProdutoDao produtoDao;
	private EntityManager em;
	
	public TestesHelper(EntityManager em) {
		this.categoriaDao = new CategoriaDao();
		this.produtoDao = new ProdutoDao(em);
		this.em = em;
	}

	public void cadastrarProduto(Categoria categoria, String nome, String descricao, BigDecimal preco) {

		Produto p = Produto.builder()
				.nome(nome)
				.descricao(descricao)
				.categoria(categoria)
				.preco(preco)
				.build();
		
		produtoDao.cadastrar(p);
		em.flush();
	}

	public Categoria cadastroCategoria(String nome) {
		Categoria categoria = Categoria.builder().nome(nome).build();
		categoriaDao.cadastrar(categoria);
		return categoria;
	}

}
