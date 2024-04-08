package tech.ada.sb.service.operacao.tarifa;

import java.math.BigDecimal;

public class TaxaPJ implements Tarifa {

	@Override
	public BigDecimal getTaxa() {
		return BigDecimal.valueOf(0.005);
	}

}
