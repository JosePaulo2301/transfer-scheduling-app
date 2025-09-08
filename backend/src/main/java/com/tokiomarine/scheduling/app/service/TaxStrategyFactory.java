package com.tokiomarine.scheduling.app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.tokiomarine.scheduling.app.service.busuness.TaxStrategy;

@Configuration
public class TaxStrategyFactory {

    private final List<TaxCalculate> estrategias;

    public TaxStrategyFactory() {
        estrategias = List.of(
            new TaxCalculate(0, 0, new BigDecimal("3.00"), new BigDecimal("0.025")),
            new TaxCalculate(1, 10, new BigDecimal("12.00"), BigDecimal.ZERO),
            new TaxCalculate(11, 20, BigDecimal.ZERO, new BigDecimal("0.082")),
            new TaxCalculate(21, 30, BigDecimal.ZERO, new BigDecimal("0.069")),
            new TaxCalculate(31, 40, BigDecimal.ZERO, new BigDecimal("0.047")),
            new TaxCalculate(41, 50, BigDecimal.ZERO, new BigDecimal("0.017"))
        );
    }

    public TaxStrategy getStrategy(int dias) {
        return estrategias.stream()
            .filter(e -> e.aplicaPara(dias))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Não permitida a transferência: "));
    }
}
