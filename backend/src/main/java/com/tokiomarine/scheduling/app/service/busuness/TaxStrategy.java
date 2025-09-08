package com.tokiomarine.scheduling.app.service.busuness;

import java.math.BigDecimal;

public interface TaxStrategy {
	BigDecimal calculate(BigDecimal value);
}
