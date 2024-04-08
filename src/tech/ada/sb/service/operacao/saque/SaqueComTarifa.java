package tech.ada.sb.service.operacao.saque;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.ClientePJ;
import tech.ada.sb.model.Conta;
import tech.ada.sb.service.operacao.tarifa.Tarifa;
import tech.ada.sb.service.operacao.tarifa.Tarifavel;

public class SaqueComTarifa implements Saque<Conta<ClientePJ>>, Tarifavel {
	
	private Tarifa tarifa;
	
	public SaqueComTarifa(Tarifa tarifa) {
		super();
		this.tarifa = tarifa;
	}

	@Override
	public void sacar(BigDecimal valor, Conta<ClientePJ> conta) throws SaldoIndisponivelException {
		BigDecimal saldoInicial = conta.getSaldo();
		BigDecimal taxa = this.calcularTarifa(valor, this.tarifa.getTaxa());
		
		if (saldoInicial.compareTo(valor.add(taxa))<0)
			throw new SaldoIndisponivelException("Valor do saque ultrapassa o saldo da conta");
		
		conta.setSaldo(saldoInicial.subtract(valor).subtract(taxa));
		
	}
	
	@Override
	public BigDecimal calcularTarifa(BigDecimal valor, BigDecimal taxa) {
		return valor.multiply(taxa);
	}

}
