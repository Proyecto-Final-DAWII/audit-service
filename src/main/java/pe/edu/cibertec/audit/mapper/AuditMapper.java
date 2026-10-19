package pe.edu.cibertec.audit.mapper;

import org.springframework.stereotype.Component;
import pe.edu.cibertec.audit.dto.request.AuditRequestDto;
import pe.edu.cibertec.audit.dto.response.AuditResponseDto;
import pe.edu.cibertec.audit.entity.AuditRecord;
import pe.edu.cibertec.audit.messaging.event.AuditEvent;

@Component
public class AuditMapper {

    // 1. Convierte lo que entra por API (Request) hacia Entidad
    public AuditRecord toEntity(AuditRequestDto request) {
        if (request == null) return null;

        AuditRecord entity = new AuditRecord();
        entity.setAssetId(request.getAssetId());
        entity.setUserId(request.getUserId());
        entity.setActionType(request.getActionType());
        entity.setPreviousState(request.getPreviousState());
        entity.setNewState(request.getNewState());
        entity.setDetails(request.getDetails());
        return entity;
    }

    // 2. NUEVO: Convierte lo que entra por RabbitMQ (Event) hacia Entidad
    public AuditRecord toEntity(AuditEvent event) {
        if (event == null) return null;

        AuditRecord entity = new AuditRecord();
        entity.setAssetId(event.getAssetId());
        entity.setUserId(event.getUserId());
        entity.setActionType(event.getActionType());
        entity.setPreviousState(event.getPreviousState());
        entity.setNewState(event.getNewState());
        entity.setDetails(event.getDetails());
        return entity;
    }

    // 3. Convierte lo que sale de la BD (Entidad) hacia el cliente (Response)
    public AuditResponseDto toResponseDto(AuditRecord entity) {
        if (entity == null) return null;

        AuditResponseDto response = new AuditResponseDto();
        response.setId(entity.getId());
        response.setAssetId(entity.getAssetId());
        response.setUserId(entity.getUserId());
        response.setActionType(entity.getActionType());
        response.setPreviousState(entity.getPreviousState());
        response.setNewState(entity.getNewState());
        response.setDetails(entity.getDetails());
        response.setTimestamp(entity.getTimestamp());
        return response;
    }
}