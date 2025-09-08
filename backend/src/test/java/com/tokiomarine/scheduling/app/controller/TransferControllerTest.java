package com.tokiomarine.scheduling.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokiomarine.scheduling.app.model.Transfer;
import com.tokiomarine.scheduling.app.model.TransferRequestDTO;
import com.tokiomarine.scheduling.app.service.TransferService;

@WebMvcTest(TransferController.class)
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransferService service;
    
    
    @MockBean
    private TransferService transferService;

    @Test
     void deveAgendarTransferenciaComSucesso() throws Exception {
        TransferRequestDTO request = new TransferRequestDTO();
        request.setSourceAccount("1234567890");
        request.setDestinationAccount("0987654321");
        request.setValueTransfer(new BigDecimal("1000.00"));
        request.setDateTransfer(LocalDateTime.now().plusDays(10));

        com.tokiomarine.scheduling.app.model.Transfer transfer = new com.tokiomarine.scheduling.app.model.Transfer();
        transfer.setId(1L);
        transfer.setSourceAccount(request.getSourceAccount());
        transfer.setDestinationAccount(request.getDestinationAccount());
        transfer.setValueTransfer(request.getValueTransfer());
        transfer.setTax(new BigDecimal("12.00"));
        transfer.setDateScheduler(LocalDateTime.now());
        transfer.setDateTransfer(request.getDateTransfer());

        when(transferService.scheduler(any())).thenReturn(transfer);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        mockMvc.perform(post("/api/v1/create")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.tax").value(12.00))
            .andExpect(jsonPath("$.sourceAccount").value("1234567890"));
        
    }

    void deveListarTransferencias() throws Exception {
    List<Transfer> lista = List.of(
        new Transfer(1L,
                     "123",
                     "321",
                     new BigDecimal("100.00"),
                     new BigDecimal("10.00"),
                     LocalDateTime.now(),
                     LocalDateTime.now())
    );

    when(transferService.listScheduler()).thenReturn(lista);

    mockMvc.perform(get("/api/v1/list"))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$[0].id").value(1))
    .andExpect(jsonPath("$[0].sourceAccount").value("123"));
    }

    void deveRetornarBadRequestQuandoDadosInvalidos() throws Exception {
        TransferRequestDTO request = new TransferRequestDTO();

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

     mockMvc.perform(post("/api/v1/list")
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
         
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHaTransferencias() throws Exception {
    when(transferService.listScheduler()).thenReturn(List.of());

    mockMvc.perform(get("/api/v1/list"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));
}
}