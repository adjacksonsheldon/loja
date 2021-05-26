package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	/*
	 * Não tem injeção de dependência pois o foco do curso/projeto era a utilização
	 * do JPA puro.
	 */
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jqpl = "SELECT p FROM Produto p ";
		return em.createQuery(jqpl, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jqpl = "SELECT p FROM Produto p WHERE p.nome = :nome";

		return em.createQuery(jqpl, Produto.class).setParameter("nome", nome).getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String nome) {

		return em.createNamedQuery("Produto.buscarPorNomeDaCategoria", Produto.class).setParameter("nome", nome)
				.getResultList();
	}

	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		String jqpl = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";

		return em.createQuery(jqpl, BigDecimal.class).setParameter("nome", nome).getSingleResult();
	}

	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p";
		String separador = "";
		String complemento = "";

		if (nome != null && nome.trim().isEmpty()) {
			complemento = complemento + separador + " p.nome = :nome ";
			complemento = " AND ";
		}

		if (preco != null) {
			complemento = complemento + separador + " p.preco = :preco ";
			complemento = " AND ";
		}

		if (dataCadastro != null) {
			complemento = complemento + separador + " p.dataCadastro = :dataCadastro ";
			complemento = " AND ";
		}

		if (!complemento.isEmpty()) {
			jpql = jpql + " WHERE " + complemento;
		}

		TypedQuery<Produto> typedQuery = em.createQuery(jpql, Produto.class);

		if (nome != null && nome.trim().isEmpty()) {
			typedQuery.setParameter("nome", nome);
		}

		if (preco != null) {
			typedQuery.setParameter("preco", preco);
		}

		if (dataCadastro != null) {
			typedQuery.setParameter("dataCadastro", dataCadastro);
		}

		return typedQuery.getResultList();
	}

	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);

		Predicate filtros = builder.and();
		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}

		if (preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}

		if (dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}

		query.where(filtros);

		return em.createQuery(query).getResultList();
	}
}
