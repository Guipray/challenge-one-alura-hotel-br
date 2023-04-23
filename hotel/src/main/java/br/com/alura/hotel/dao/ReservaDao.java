package br.com.alura.hotel.dao;

import javax.persistence.EntityManager;

import br.com.alura.hotel.modelo.Reserva;

public class ReservaDao {

	private EntityManager em;

	public ReservaDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Reserva reserva) {
		this.em.persist(reserva);
	}

	public void atualizar(Reserva reserva) {
		this.em.merge(reserva);
	}

	public void remover(Reserva reserva) {
		this.remover(reserva);
	}

	public Reserva buscarPorId(Long id) {
		return em.find(Reserva.class, id);
	}

}
