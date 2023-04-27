package br.com.alura.hotel.controller;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.hotel.dao.HospedeDao;
import br.com.alura.hotel.modelo.Hospede;
import br.com.alura.hotel.util.JPAUtil;

public class HospedeController {

	private HospedeDao hospedeDao;
	
	public HospedeController() {
		EntityManager entityManager = JPAUtil.getEntityManager();
		this.hospedeDao = new HospedeDao(entityManager);
	}
	
	public void cadastrar(Hospede hospede) {
		this.hospedeDao.cadastrar(hospede);
	}
	
	public void atualizar(Hospede hospede) {
		this.hospedeDao.atualizar(hospede);
	}
	
	public void remover(Hospede hospede) {
		this.hospedeDao.remover(hospede);
	}
	
	public Hospede buscarPorId(Long id) {
		return this.hospedeDao.buscarPorId(id);
	}
	
	public List<Hospede> buscarTodos() {
		return this.hospedeDao.buscarTodos();
	}
	
	public Hospede buscarPorIdDaReserva(Long id) {
		return this.hospedeDao.buscarPorIdDaReserva(id);
	}
	
	public List<Hospede> buscarPorParametros(String nome, String sobrenome, LocalDate dataNascimento,
			String nacionalidade, String telefone) {
		return this.hospedeDao.buscarPorParametros(nome, sobrenome, dataNascimento, nacionalidade, telefone);
	}
	
}
