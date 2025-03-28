package com.backendAlianza.prueba.service;

import com.backendAlianza.prueba.dto.ClientDTO;
import com.backendAlianza.prueba.entity.ClientEntity;
import com.backendAlianza.prueba.mapper.ClientMapper;
import com.backendAlianza.prueba.repository.ClientRepository;
import com.backendAlianza.prueba.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImpl(clientRepository, clientMapper);
    }

    @Test
    void whenCreateClient_thenReturnClientDTO() {
        // Given
        ClientDTO inputDto = new ClientDTO();
        inputDto.setSharedKey("key1");
        
        ClientEntity entity = new ClientEntity();
        entity.setSharedKey("key1");
        
        when(clientMapper.toEntity(any(ClientDTO.class))).thenReturn(entity);
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(entity);
        when(clientMapper.toDto(any(ClientEntity.class))).thenReturn(inputDto);

        // When
        ClientDTO result = clientService.createClient(inputDto);

        // Then
        assertNotNull(result);
        assertEquals("key1", result.getSharedKey());
    }

    @Test
    void whenGetAllClients_thenReturnClientDTOList() {
        // Given
        List<ClientEntity> entities = Arrays.asList(new ClientEntity(), new ClientEntity());
        when(clientRepository.getClients()).thenReturn(entities);
        when(clientMapper.toDto(any(ClientEntity.class))).thenReturn(new ClientDTO());

        // When
        List<ClientDTO> result = clientService.getAllClients();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }
} 