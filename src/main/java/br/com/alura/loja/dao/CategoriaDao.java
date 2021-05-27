package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoriaDao {

	private final int batchSize = 3;
	/*
	 * Não tem injeção de dependência pois o foco do curso/projeto era a utilização
	 * do JPA puro.
	 */
	private final EntityManager em = JPAUtil.getEntityManager();;

	public void cadastrar(Categoria categoria) {
		try {
			em.getTransaction().begin();
			this.em.persist(categoria);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public void cadastrar(List<Categoria> categorias) {

		try {
			em.getTransaction().begin();

			for (int i = 0; i < categorias.size(); i++) {
				if (i > 0 && i % batchSize == 0) {
					em.flush();
					em.clear();
				}
				em.persist(categorias.get(i));
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}

	public void remover(Categoria categoria) {
		categoria = this.em.merge(categoria);
		this.em.remove(categoria);
	}

}
