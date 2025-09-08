package com.tokiomarine.scheduling.app.enums;

import java.math.BigDecimal;


public enum TaxCalculateEnum {

    A (0, 0,   new BigDecimal("3.00"), new BigDecimal("0.025")),
    B (1, 10,  new BigDecimal("12.00"), BigDecimal.ZERO),
    C (11, 20, BigDecimal.ZERO, new BigDecimal("0.082")),
    D (21, 30, BigDecimal.ZERO, new BigDecimal("0.069")),
    E (31, 40, BigDecimal.ZERO, new BigDecimal("0.047")),
    F (41, 50, BigDecimal.ZERO, new BigDecimal("0.017"));

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