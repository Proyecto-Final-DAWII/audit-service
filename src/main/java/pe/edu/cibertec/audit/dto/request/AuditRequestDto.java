package pe.edu.cibertec.audit.dto.request;

import lombok.Data;

@Data
public class AuditRequestDto {
    private String assetId;
    private String userId;
    private String actionType;
    private String previousState;
    private String newState;
    private String details;
}