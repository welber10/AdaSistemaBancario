package tech.ada.sb.model;

import java.math.BigDecimal;

import tech.ada.sb.service.operacao.rentabiliza.Rentabiliza;

public class ContaPoupanca extends Conta<ClientePF> {
	
	private Rentabiliza rentabilidade;

	public ContaPoupanca(ClientePF titular, String numeroConta, BigDecimal saldo, Rentabiliza rentabilidade) {
		super(titular, numeroConta, saldo);
		this.rentabilidade = rentabilidade;
	}
	
	public Rentabiliza getRentabilidade() {
		return rentabilidade;
	}
	
}
