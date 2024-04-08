package tech.ada.sb.service.operacao.transferencia;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.Conta;

public class TransferenciaBancaria implements Transferencia<Conta<?>> {

	@Override
	public void transferir(BigDecimal valor, Conta<?> contaOrigem, Conta<?> contaDestino) throws SaldoIndisponivelException {
		BigDecimal saldoInicial = contaOrigem.getSaldo();
		
		if (saldoInicial.compareTo(valor)<0)
			throw new SaldoIndisponivelException("Valor da transferencia ultrapassa o saldo da conta de origem");
		
		contaOrigem.setSaldo(saldoInicial.subtract(valor));
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
	}

}
