package pe.edu.cibertec.audit.messaging.event;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AuditEvent {
    private String assetId;
    private String userId;
    private String actionType;
    private String previousState;
    private String newState;
    private String details;
    private LocalDateTime eventDate;
}