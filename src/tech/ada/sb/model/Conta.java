package tech.ada.sb.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Conta<T extends Cliente> {
	
	private String numeroConta;
	private BigDecimal saldo;
	private LocalDateTime dataCriacao;
	private Cliente titular;
	
	protected Conta(T titular, String numeroConta, BigDecimal saldo) {
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.dataCriacao = LocalDateTime.now();
		this.titular = titular;
	}
	
	public BigDecimal getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}
	
	public Cliente getTitular() {
		return titular;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [numeroConta=" + numeroConta + "]";
	}

}
