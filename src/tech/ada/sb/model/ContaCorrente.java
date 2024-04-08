package tech.ada.sb.model;

import java.math.BigDecimal;

public class ContaCorrente extends Conta<Cliente> {
	
	public ContaCorrente(Cliente titular, String numeroConta, BigDecimal saldo) {
		super(titular, numeroConta, saldo);
	}
	
}
