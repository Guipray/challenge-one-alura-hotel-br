package br.com.alura.hotel.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.hotel.modelo.Hospede;

public class HospedeDao {

	private EntityManager em;
	
	public HospedeDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Hospede hospede) {
		this.em.persist(hospede);
	}
	
	public void atualizar(Hospede hospede) {
		this.em.merge(hospede);
	}
	
	public void remover(Hospede hospede) {
		this.remover(hospede);
	}
	
	public Hospede buscarPorId(Long id) {
		return em.find(Hospede.class, id);
	}
	
	public List<Hospede> buscarTodos() {
		String jpql = "SELECT h FROM Hospede h";
		return em.createQuery(jpql, Hospede.class).getResultList();
	}
	
	public Hospede buscarPorIdDaReserva(Long id) {
		String jpql = "SELECT h FROM Hospede h WHERE h.reserva.id = :id";
		return em.createQuery(jpql,Hospede.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Hospede> buscarPorParametros(String nome, String sobrenome, LocalDate dataNascimento,
			String nacionalidade, String telefone) {
		String jpql = "SELECT h FROM Hospede h WHERE 1=1 ";
		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND h.nome = :nome ";
		}
		if (sobrenome != null && !sobrenome.trim().isEmpty()) {
			jpql += " AND h.sobrenome = :sobrenome ";
		}
		if (dataNascimento != null) {
			jpql += " AND h.dataNascimento = :dataNascimento";
		}
		if (nacionalidade != null && !nacionalidade.trim().isEmpty()) {
			jpql += " AND h.nacionalidade = :nacionalidade";
		}
		if (telefone != null && !telefone.trim().isEmpty()) {
			jpql += " AND h.telefone = :telefone";
		}
		TypedQuery<Hospede> query = em.createQuery(jpql, Hospede.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}
		if (sobrenome != null && !sobrenome.trim().isEmpty()) {
			query.setParameter("sobrenome", sobrenome);
		}
		if (dataNascimento != null) {
			query.setParameter("dataNascimento", dataNascimento);
		}
		if (nacionalidade != null && !nacionalidade.trim().isEmpty()) {
			query.setParameter("nacionalidade", nacionalidade);
		}
		if (telefone != null && !telefone.trim().isEmpty()) {
			query.setParameter("telefone", telefone);
		}
		
		return query.getResultList();
	}
	
}
