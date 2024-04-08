package tech.ada.sb.service.operacao.saque;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.Conta;
import tech.ada.sb.service.operacao.OperacaoBancaria;

public interface Saque<T extends Conta<?>> extends OperacaoBancaria {
	
	void sacar(BigDecimal valor, T conta) throws SaldoIndisponivelException;

}
