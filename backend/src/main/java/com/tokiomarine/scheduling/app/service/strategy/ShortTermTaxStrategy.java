package com.tokiomarine.scheduling.app.service.strategy;

import java.math.BigDecimal;

import com.tokiomarine.scheduling.app.enums.TaxCalculateEnum;
import com.tokiomarine.scheduling.app.service.busuness.TaxStrategy;

public class ShortTermTaxStrategy implements TaxStrategy {

	@Override
	public BigDecimal calculate(BigDecimal value) {
		return TaxCalculateEnum.SHORT_TERM.calcularTaxa(value);
	}
}