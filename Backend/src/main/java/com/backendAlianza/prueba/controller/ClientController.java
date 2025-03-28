package com.backendAlianza.prueba.controller;

import com.backendAlianza.prueba.dto.ClientDTO;
import com.backendAlianza.prueba.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Api(tags = "Client Management API")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    @ApiOperation("Get all clients or search by filters")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Successfully retrieved clients"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<ClientDTO>> getClients(
            @RequestParam(required = false) String sharedKey,
            @RequestParam(required = false) String businessId,
            @RequestParam(required = false) String email) {
        
        log.info("Fetching clients with filters: sharedKey={}, businessId={}, email={}", 
                 sharedKey, businessId, email);

        List<ClientDTO> clients;
        if (sharedKey != null || businessId != null || email != null) {
            clients = clientService.advancedSearch(sharedKey, businessId, email);
        } else {
            clients = clientService.getAllClients();
        }
        
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    @ApiOperation("Create a new client")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Client created successfully"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        log.info("Creating new client: {}", clientDTO);
        ClientDTO newClient = clientService.createClient(clientDTO);
        return ResponseEntity.ok(newClient);
    }
} 