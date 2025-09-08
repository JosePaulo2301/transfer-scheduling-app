package com.tokiomarine.scheduling.app.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tokiomarine.scheduling.app.model.Transfer;
import com.tokiomarine.scheduling.app.model.TransferRequestDTO;
import com.tokiomarine.scheduling.app.repository.TransferRepository;
import com.tokiomarine.scheduling.app.service.busuness.TaxStrategy;

@Service
public class TransferService {
	
    private final TransferRepository repository;
    private final TaxStrategyFactory strategyFactory;

    public TransferService(TransferRepository repository, TaxStrategyFactory strategyFactory) {
        this.repository = repository;
        this.strategyFactory = strategyFactory;
    }
    
   
	public Transfer scheduler(TransferRequestDTO dto) {
        LocalDateTime today = LocalDateTime.now();
        int days = (int) ChronoUnit.DAYS.between(today, dto.getDateTransfer());

        TaxStrategy strategy = strategyFactory.getStrategy(days);
        BigDecimal taxa = strategy.calculate(dto.getValueTransfer());

        Transfer transfer = new Transfer();
        
        transfer.setSourceAccount(dto.getSourceAccount());
        transfer.setDestinationAccount(dto.getDestinationAccount());
        transfer.setValueTransfer(dto.getValueTransfer());
        transfer.setTax(taxa);
        transfer.setDateScheduler(today);
        transfer.setDateTransfer(dto.getDateTransfer());

        return repository.save(transfer);
    }

    public List<Transfer> listScheduler() {
        return repository.findAll();
    }
	
	
}