package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroPedido {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		CadastroPedido cadastroBiDirecional = new CadastroPedido();
		cadastroBiDirecional.cadastrarProduto(em);	
		
		Produto produto = cadastroBiDirecional.cadastrarProduto(em);
		Pedido pedido = cadastroBiDirecional.gerarPedido(em, produto);		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
//		System.out.println(pedidoDao.valorTotalVendido());
				
		pedidoDao.relatorioVendas().forEach(System.out::println);
		
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

	private Cliente cadastrarCliente(EntityManager em) {
		Cliente cliente = Cliente.builder()
									.nome("Sheldon")
									.cpf("00123456789")
									.build();
		
		ClienteDao clienteDao = new ClienteDao(em);
		clienteDao.cadastrar(cliente);
		return cliente;
	}
	
	private Produto cadastrarProduto(EntityManager em) {
		Categoria categoria = Categoria.builder().nome("Celulares").build();
		
		Produto p = Produto.builder()
				.nome("Mi Note 9")
				.descricao("Cellular Xioami")
				.categoria(categoria)
				.preco(new BigDecimal("9.90"))
				.build();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(p);
		em.flush();
		
		return p;
	}
}
