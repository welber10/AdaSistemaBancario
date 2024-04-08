package tech.ada.sb.service.operacao.deposito;

import java.math.BigDecimal;

import tech.ada.sb.model.Conta;
import tech.ada.sb.service.operacao.OperacaoBancaria;

public interface Deposito <T extends Conta<?>> extends OperacaoBancaria {
	
	void depositar(BigDecimal valor, T conta);

}
