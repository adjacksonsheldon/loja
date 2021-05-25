package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {

	/*
	 * Não tem injeção de dependência pois o foco do curso/projeto era a utilização do JPA puro.
	 */
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		this.em.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = this.em.merge(cliente);
		this.em.remove(cliente);
	}	
}
