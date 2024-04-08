package tech.ada.sb.service.operacao.transferencia;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.Conta;
import tech.ada.sb.service.operacao.OperacaoBancaria;

public interface Transferencia<T extends Conta<?>> extends OperacaoBancaria {
	
	void transferir(BigDecimal valor, T contaOrigem, T contaDestino) throws SaldoIndisponivelException;

}
