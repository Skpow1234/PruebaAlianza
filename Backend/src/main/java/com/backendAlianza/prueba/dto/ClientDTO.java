package com.backendAlianza.prueba.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Client Data Transfer Object")
public class ClientDTO {
    @ApiModelProperty(value = "Client's shared key", required = true)
    @NotBlank(message = "Shared key is required")
    private String sharedKey;
    
    @ApiModelProperty(value = "Client's business ID", required = true)
    @NotBlank(message = "Business ID is required")
    private String businessId;
    
    @ApiModelProperty(value = "Client's email address", required = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @ApiModelProperty(value = "Client's phone number", required = true)
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]+$", message = "Phone must contain only numbers")
    private String phone;
} 