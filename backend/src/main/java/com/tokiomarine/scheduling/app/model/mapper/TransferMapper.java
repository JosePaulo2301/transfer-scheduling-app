package com.tokiomarine.scheduling.app.model.mapper;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;

import com.tokiomarine.scheduling.app.model.Transfer;
import com.tokiomarine.scheduling.app.model.TransferRequestDTO;

@Configuration
public class TransferMapper {
	
	public Transfer toEntity(TransferRequestDTO dto) {
		Transfer t = new Transfer();
		t.setDestinationAccount(dto.getSourceAccount());
		t.setDestinationAccount(dto.getDestinationAccount());
		t.setValueTransfer(dto.getValueTransfer());
		t.setDateScheduler(LocalDateTime.now());
		t.setDateTransfer(dto.getDateTransfer());
		return t;
		
	}
}
