package tech.ada.sb.service.operacao.investimento;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.ContaInvestimento;

public class InvestimentoComRendimento implements Investimento<ContaInvestimento> {

	@Override
	public void investir(BigDecimal valor, ContaInvestimento conta) throws SaldoIndisponivelException {
		BigDecimal saldoInicial = conta.getSaldo();
		
		if (saldoInicial.compareTo(valor)<0)
			throw new SaldoIndisponivelException("Valor a ser investido ultrapassa o saldo disponível da conta investimento.");
		
		conta.setSaldo(saldoInicial.subtract(valor));
		conta.setInvestimento(conta.getInvestimento().add(valor).add(conta.getRentabilidade().calcular(valor)));
	}
	
}
