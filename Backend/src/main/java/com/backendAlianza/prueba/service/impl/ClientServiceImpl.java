package com.backendAlianza.prueba.service.impl;

import com.backendAlianza.prueba.dto.ClientDTO;
import com.backendAlianza.prueba.entity.ClientEntity;
import com.backendAlianza.prueba.mapper.ClientMapper;
import com.backendAlianza.prueba.repository.ClientRepository;
import com.backendAlianza.prueba.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDTO> getAllClients() {
        log.debug("Getting all clients");
        return clientRepository.getClients().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> searchBySharedKey(String sharedKey) {
        log.debug("Searching clients by shared key: {}", sharedKey);
        return clientRepository.findBySharedKey(sharedKey).stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> advancedSearch(String sharedKey, String businessId, String email) {
        log.debug("Advanced search with filters - sharedKey: {}, businessId: {}, email: {}", 
                 sharedKey, businessId, email);
        return clientRepository.findByFilters(sharedKey, businessId, email).stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        log.debug("Creating new client: {}", clientDTO);
        ClientEntity entity = clientMapper.toEntity(clientDTO);
        ClientEntity savedEntity = clientRepository.save(entity);
        return clientMapper.toDto(savedEntity);
    }
} 