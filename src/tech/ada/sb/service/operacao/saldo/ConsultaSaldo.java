package tech.ada.sb.service.operacao.saldo;

import java.math.BigDecimal;

import tech.ada.sb.model.Conta;

public class ConsultaSaldo implements Saldo<Conta<?>> {

	@Override
	public BigDecimal consultar(Conta<?> conta) {
		return conta.getSaldo();
	}

}
