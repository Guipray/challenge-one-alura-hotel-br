package br.com.alura.hotel.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.hotel.dao.HospedeDao;
import br.com.alura.hotel.dao.ReservaDao;
import br.com.alura.hotel.modelo.Hospede;
import br.com.alura.hotel.modelo.Reserva;
import br.com.alura.hotel.util.JPAUtil;

public class Teste {

	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();

		HospedeDao hospedeDao = new HospedeDao(em);
		ReservaDao reservaDao = new ReservaDao(em);
		//Hospede hospede = new Hospede("Guilherme", "Eufrásio", LocalDate.parse("0001-01-01"), "brasileiro", "12345678912");
		//Reserva reserva = new Reserva(LocalDate.parse("2023-04-22"), LocalDate.parse("2023-04-22"), BigDecimal.valueOf(40), "dinheiro", hospede);
		
		em.getTransaction().begin();
		
		List<Hospede> hospede = hospedeDao.buscarPorParametros(null, null, null, null, null);
		hospede.forEach(h -> System.out.println(h.getNome()));
		
		List<Reserva> reserva = reservaDao.buscarPorParametros(null, null, null, null);
		reserva.forEach(r -> System.out.println(r.getId()));
		
		
		em.getTransaction().commit();
		em.close();
	}

}

