package com.tokiomarine.scheduling.app.service.busuness;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TaxCalculateService {
	BigDecimal calculate(LocalDateTime scheduler, LocalDateTime dateTrasnfer, BigDecimal value, TaxStrategy taxStrategy);
}
