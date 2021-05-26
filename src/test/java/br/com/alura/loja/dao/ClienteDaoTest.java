package br.com.alura.loja.dao;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.util.JPAUtil;

public class ClienteDaoTest {

	private EntityManager em = JPAUtil.getEntityManager();
	private ClienteDao clienteDao = new ClienteDao(em);

	@Test
	public void deveriaIncluirUmCliente() {
		em.getTransaction().begin();		
		Cliente cliente = new Cliente("Sheldon", "12345678911");
		clienteDao.cadastrar(cliente);
		
		assertTrue(cliente.getId() == 1);
		
		em.close();
	}
}
