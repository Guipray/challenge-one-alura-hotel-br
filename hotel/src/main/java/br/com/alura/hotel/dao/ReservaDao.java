package br.com.alura.hotel.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.hotel.modelo.Reserva;

public class ReservaDao {

	private EntityManager em;

	public ReservaDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Reserva reserva) {
		this.em.getTransaction().begin();
		this.em.persist(reserva);
		this.em.getTransaction().commit();
	}

	public void atualizar(Reserva reserva) {
		this.em.getTransaction().begin();
		this.em.merge(reserva);
		this.em.getTransaction().commit();
	}

	public void remover(Reserva reserva) {
		this.em.getTransaction().begin();
		this.em.remove(reserva);
		this.em.getTransaction().commit();
	}

	public Reserva buscarPorId(Long id) {
		return em.find(Reserva.class, id);
	}
	
	public List<Reserva> buscarTodos() {
		String jpql = "SELECT r FROM Reserva r";
		return em.createQuery(jpql, Reserva.class).getResultList();
	}
	
	public Reserva buscarPorIdDoHospede(Long id) {
		String jpql = "SELECT r FROM Reserva r WHERE r.hospede.id = :id";
		return em.createQuery(jpql, Reserva.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Reserva> buscarPorParametros(LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor,
			String formaPagamento) {
		String jpql = "SELECT r FROM Reserva r WHERE 1=1 ";
		if (dataEntrada != null) {
			jpql += " AND r.dataEntrada = :dataEntrada ";
		}
		if (dataSaida != null) {
			jpql += " AND r.dataSaida = :dataSaida ";
		}
		if (valor != null) {
			jpql += " AND r.valor = :valor ";
		}
		if (formaPagamento != null && !formaPagamento.trim().isEmpty()) {
			jpql += " AND r.formaPagamento = :formaPagamento ";
		}
		TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class);
		if (dataEntrada != null){
			query.setParameter("dataEntrada", dataEntrada);
		}
		if (dataSaida != null) {
			query.setParameter("dataSaida", dataSaida);
		}
		if (valor != null) {
			query.setParameter("valor", valor);
		}
		if (formaPagamento != null && !formaPagamento.trim().isEmpty()) {
			query.setParameter("formaPagamento", formaPagamento);
		}
		
		return query.getResultList();
	}

}
