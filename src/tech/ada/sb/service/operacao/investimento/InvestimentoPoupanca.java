package tech.ada.sb.service.operacao.investimento;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.ContaPoupanca;

public class InvestimentoPoupanca implements Investimento<ContaPoupanca> {

	@Override
	public void investir(BigDecimal valor, ContaPoupanca conta) throws SaldoIndisponivelException {
		conta.setSaldo(conta.getSaldo().add(conta.getRentabilidade().calcular(conta.getSaldo())));
	}

}
