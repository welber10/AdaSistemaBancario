package tech.ada.sb.service.operacao.deposito;

import java.math.BigDecimal;

import tech.ada.sb.model.Conta;

public class DepositoConta implements Deposito<Conta<?>> {

	@Override
	public void depositar(BigDecimal valor, Conta<?> conta) {
		conta.setSaldo(conta.getSaldo().add(valor));
	}

}
