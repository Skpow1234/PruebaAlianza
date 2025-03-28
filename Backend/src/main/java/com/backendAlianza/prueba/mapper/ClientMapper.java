package com.backendAlianza.prueba.mapper;

import com.backendAlianza.prueba.dto.ClientDTO;
import com.backendAlianza.prueba.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "dataAdded", expression = "java(getCurrentDateTime())")
    ClientEntity toEntity(ClientDTO dto);
    
    ClientDTO toDto(ClientEntity entity);
    
    @Named("getCurrentDateTime")
    default String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
} 