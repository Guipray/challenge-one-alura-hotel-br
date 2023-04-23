package br.com.alura.hotel.dao;

import javax.persistence.EntityManager;

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
}
