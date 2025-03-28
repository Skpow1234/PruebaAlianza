package com.backendAlianza.prueba.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    private String sharedKey;
    private String businessId;
    private String email;
    private String phone;
    private String dataAdded;
} 