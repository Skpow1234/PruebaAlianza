package com.backendAlianza.prueba.repository;

import com.backendAlianza.prueba.entity.ClientEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Repository
public class ClientRepository {
    @Getter
    private final List<ClientEntity> clients = new ArrayList<>();

    public ClientEntity save(ClientEntity client) {
        clients.add(client);
        return client;
    }

    public List<ClientEntity> findBySharedKey(String sharedKey) {
        return clients.stream()
                .filter(client -> client.getSharedKey().contains(sharedKey))
                .collect(Collectors.toList());
    }

    public List<ClientEntity> findByFilters(String sharedKey, String businessId, String email) {
        return clients.stream()
                .filter(client -> 
                    (sharedKey == null || client.getSharedKey().contains(sharedKey)) &&
                    (businessId == null || client.getBusinessId().contains(businessId)) &&
                    (email == null || client.getEmail().contains(email)))
                .collect(Collectors.toList());
    }
} 