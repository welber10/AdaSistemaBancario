package tech.ada.sb.service.operacao.investimento;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.Conta;
import tech.ada.sb.service.operacao.OperacaoBancaria;

public interface Investimento <T extends Conta<?>> extends OperacaoBancaria {
	
	void investir(BigDecimal valor, T conta) throws SaldoIndisponivelException;

}
