package br.com.alura.loja.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class PedidoDaoTest {
	
	private EntityManager em = JPAUtil.getEntityManager();
	private ProdutoDao produtoDao;
	private CategoriaDao categoriaDao;
	private ClienteDao clienteDao;
	private PedidoDao pedidoDao;
	private Produto produto;

	@Before
	public void setup() {
		produtoDao = new ProdutoDao(em);
		categoriaDao = new CategoriaDao(em);
		clienteDao = new ClienteDao(em);
		pedidoDao = new PedidoDao(em);
		
		em.getTransaction().begin();

	}
	
	@Test
	public void test() {
		produto = cadastrarProduto(em);
		categoriaDao = new CategoriaDao(em);
		
		Pedido p = gerarPedido(em, produto);
		pedidoDao.cadastrar(p);
		assertTrue(p.getId().equals(1L));
	}

	private Produto cadastrarProduto(EntityManager em) {
		Categoria categoria = this.gerarCategoria(em);
		
		Produto produto = Produto.builder()
				.nome("Mi Note 9")
				.descricao("Cellular Xioami")
				.categoria(categoria)
				.preco(new BigDecimal("9.90"))
				.build();		
		
		produtoDao.cadastrar(produto);
		em.flush();
		
		return produto;
	}

	private Categoria gerarCategoria(EntityManager em) {
		Categoria categoria = Categoria.builder().nome("Celulares").build();
		categoriaDao = new CategoriaDao(em);
		categoriaDao.cadastrar(categoria);
		return categoria;
	}

	private Cliente cadastrarCliente(EntityManager em) {
		Cliente cliente = new Cliente("Sheldon", "00123456789");
		
		clienteDao = new ClienteDao(em);
		clienteDao.cadastrar(cliente);
		return cliente;
	}
	
	private Pedido gerarPedido(EntityManager em, Produto produto) {
		Cliente cliente = this.cadastrarCliente(em);
		
		Pedido pedido = Pedido.builder()
							.cliente(cliente)
							.build();

		ItemPedido itemPedido = ItemPedido.builder()
											.quantidade(10)
											.pedido(pedido)
											.produto(produto)
											.precoUnitario(BigDecimal.TEN)
											.build();
		pedido.adicionarItem(itemPedido);
		return pedido;
	}
}
