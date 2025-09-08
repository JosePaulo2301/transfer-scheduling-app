package com.tokiomarine.scheduling.app.service;

import java.math.BigDecimal;

import com.tokiomarine.scheduling.app.service.busuness.TaxStrategy;

public class TaxCalculated implements TaxStrategy {
	

    private final int minDays;
    private final int maxDays;
    private final BigDecimal valorFixo;
    private final BigDecimal percentual;

    @Override
    public BigDecimal calculate(BigDecimal valorTransferencia) {
        return valorFixo.add(valorTransferencia.multiply(percentual));
    }

    
	   public TaxCalculated(int minDays, int maxDays, BigDecimal valorFixo, BigDecimal percentual) {
	        this.minDays = minDays;
	        this.maxDays = maxDays;
	        this.valorFixo = valorFixo;
	        this.percentual = percentual;
	    }
	
	
	   public boolean aplicaPara(int dias) {
	        return dias >= minDays && dias <= maxDays;
	    }

}
