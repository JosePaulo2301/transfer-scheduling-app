package com.tokiomarine.scheduling.app.service.strategy;

import java.math.BigDecimal;

import com.tokiomarine.scheduling.app.enums.TaxCalculateEnum;
import com.tokiomarine.scheduling.app.service.busuness.TaxStrategy;

public class TaxCalculatedD implements TaxStrategy {

	@Override
	public BigDecimal calculate(BigDecimal value) {
		return TaxCalculateEnum.D.calcularTaxa(value);
	}
}