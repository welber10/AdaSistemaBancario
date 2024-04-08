package tech.ada.sb.model;

import java.math.BigDecimal;

import tech.ada.sb.service.operacao.rentabiliza.Rentabiliza;

public class ContaInvestimento extends Conta<Cliente> {
	
	/**
	 * Valor da conta que está investido.
	 */
	private BigDecimal investimento;
	
	private Rentabiliza rentabilidade;

	public ContaInvestimento(Cliente titular, String numeroConta, BigDecimal saldo, Rentabiliza rentabilidade) {
		super(titular, numeroConta, saldo);
		this.investimento = BigDecimal.ZERO;
		this.rentabilidade = rentabilidade;
	}
	
	public BigDecimal getInvestimento() {
		return investimento;
	}
	
	public void setInvestimento(BigDecimal investimento) {
		this.investimento = investimento;
	}

	public Rentabiliza getRentabilidade() {
		return rentabilidade;
	}
	
}
