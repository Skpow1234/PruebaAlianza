package com.backendAlianza.prueba.controller;

import com.backendAlianza.prueba.dto.ClientDTO;
import com.backendAlianza.prueba.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenGetClients_thenReturnJsonArray() throws Exception {
        ClientDTO client = new ClientDTO();
        client.setSharedKey("key1");
        
        when(clientService.getAllClients()).thenReturn(Arrays.asList(client));

        mockMvc.perform(get("/clients"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].sharedKey").value("key1"));
    }

    @Test
    void whenCreateValidClient_thenReturnClient() throws Exception {
        ClientDTO client = new ClientDTO();
        client.setSharedKey("key1");
        client.setBusinessId("bid1");
        client.setEmail("test@test.com");
        client.setPhone("1234567890");

        when(clientService.createClient(any(ClientDTO.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.sharedKey").value("key1"));
    }
} 