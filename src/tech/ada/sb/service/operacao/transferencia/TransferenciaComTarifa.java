package tech.ada.sb.service.operacao.transferencia;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.Conta;
import tech.ada.sb.service.operacao.tarifa.Tarifa;
import tech.ada.sb.service.operacao.tarifa.Tarifavel;

public class TransferenciaComTarifa implements Transferencia<Conta<?>>, Tarifavel {

	private Tarifa tarifa;
	
	public TransferenciaComTarifa(Tarifa tarifa) {
		super();
		this.tarifa = tarifa;
	}

	@Override
	public void transferir(BigDecimal valor, Conta<?> contaOrigem, Conta<?> contaDestino) throws SaldoIndisponivelException {
		BigDecimal saldoInicial = contaOrigem.getSaldo();
		BigDecimal taxa = this.calcularTarifa(valor, this.tarifa.getTaxa());
		
		if (saldoInicial.compareTo(valor.add(taxa))<0)
			throw new SaldoIndisponivelException("Valor da transferencia ultrapassa o saldo da conta de origem");
		
		contaOrigem.setSaldo(saldoInicial.subtract(valor).subtract(taxa));
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
	}

	@Override
	public BigDecimal calcularTarifa(BigDecimal valor, BigDecimal taxa) {
		return valor.multiply(taxa);
	}

}
