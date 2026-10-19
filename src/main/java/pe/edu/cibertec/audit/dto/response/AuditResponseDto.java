package pe.edu.cibertec.audit.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AuditResponseDto {
    private Long id; // Incluimos el ID de la base de datos
    private String assetId;
    private String userId;
    private String actionType;
    private String previousState;
    private String newState;
    private String details;
    private LocalDateTime timestamp; // Incluimos la fecha de creación
}