package tech.ada.sb.service.operacao.saque;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.Conta;

public class SaqueConta implements Saque<Conta<?>> {

	@Override
	public void sacar(BigDecimal valor, Conta<?> conta) throws SaldoIndisponivelException {
		BigDecimal saldoInicial = conta.getSaldo();
		
		if (saldoInicial.compareTo(valor)<0)
			throw new SaldoIndisponivelException("Valor do saque ultrapassa o saldo da conta");
		
		conta.setSaldo(saldoInicial.subtract(valor));
	}

}
