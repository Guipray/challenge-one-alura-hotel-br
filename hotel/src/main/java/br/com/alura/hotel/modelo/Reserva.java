package br.com.alura.hotel.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "data_entrada")
	private LocalDate dataEntranda;
	@Column(name = "data_saida")
	private LocalDate dataSaida;
	private BigDecimal valor;
	@Column(name = "forma_pagamento")
	private String formaPagamento;
	
	@OneToOne(mappedBy = "reserva", fetch = FetchType.LAZY)
	private Hospede hospede;
	
	public Reserva() {
	}

	public Reserva(LocalDate dataEntranda, LocalDate dataSaida, BigDecimal valor, String formaPagamento,
			Hospede hospede) {
		this.dataEntranda = dataEntranda;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.hospede = hospede;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataEntranda() {
		return dataEntranda;
	}

	public void setDataEntranda(LocalDate dataEntranda) {
		this.dataEntranda = dataEntranda;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	
}
