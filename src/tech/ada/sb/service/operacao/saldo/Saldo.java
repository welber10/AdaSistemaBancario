package tech.ada.sb.service.operacao.saldo;

import java.math.BigDecimal;

import tech.ada.sb.model.Conta;
import tech.ada.sb.service.operacao.OperacaoBancaria;

public interface Saldo<T extends Conta<?>> extends OperacaoBancaria {
	
	BigDecimal consultar(T conta);
}
