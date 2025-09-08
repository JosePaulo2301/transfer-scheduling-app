package com.tokiomarine.scheduling.app.enums;

import java.math.BigDecimal;


public enum TaxCalculateEnum {

    IMMEDIATE      (0, 0,   new BigDecimal("3.00"), new BigDecimal("0.025")),
    SHORT_TERM     (1, 10,  new BigDecimal("12.00"), BigDecimal.ZERO),
    MEDIUM_TERM    (11, 20, BigDecimal.ZERO, new BigDecimal("0.082")),
    LONG_TERM      (21, 30, BigDecimal.ZERO, new BigDecimal("0.069")),
    EXTENDED       (31, 40, BigDecimal.ZERO, new BigDecimal("0.047")),
    VERY_LONG_TERM (41, 50, BigDecimal.ZERO, new BigDecimal("0.017"));

    private final int minDays;
    private final int maxDays;
    private final BigDecimal valorFixo;
    private final BigDecimal percentual;

    TaxCalculateEnum(int minDays, int maxDays, BigDecimal valorFixo, BigDecimal percentual) {
        this.minDays = minDays;
        this.maxDays = maxDays;
        this.valorFixo = valorFixo;
        this.percentual = percentual;
    }


    public BigDecimal calcularTaxa(BigDecimal valueTransfer) {
        return valorFixo.add(valueTransfer.multiply(percentual));
    }

}