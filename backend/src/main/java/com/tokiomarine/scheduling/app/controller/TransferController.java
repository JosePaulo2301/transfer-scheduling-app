package com.tokiomarine.scheduling.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokiomarine.scheduling.app.model.Transfer;
import com.tokiomarine.scheduling.app.model.TransferRequestDTO;
import com.tokiomarine.scheduling.app.service.TransferService;


@RestController
@RequestMapping("/transfers")
public class TransferController {


    private final TransferService transferService;
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> scheduler(@RequestBody @Valid TransferRequestDTO transferDTO) {
        Transfer transfer = transferService.scheduler(transferDTO);
        return ResponseEntity.ok(transfer);
    }

    @GetMapping
    public ResponseEntity<List<Transfer>> listScheduler() {
        List<Transfer> transfers = transferService.listScheduler();
        return ResponseEntity.ok(transfers);
    }

   }
    
