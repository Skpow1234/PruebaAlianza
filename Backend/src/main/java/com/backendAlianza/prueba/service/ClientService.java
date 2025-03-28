package com.backendAlianza.prueba.service;

import com.backendAlianza.prueba.dto.ClientDTO;
import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();
    List<ClientDTO> searchBySharedKey(String sharedKey);
    List<ClientDTO> advancedSearch(String sharedKey, String businessId, String email);
    ClientDTO createClient(ClientDTO clientDTO);
} 