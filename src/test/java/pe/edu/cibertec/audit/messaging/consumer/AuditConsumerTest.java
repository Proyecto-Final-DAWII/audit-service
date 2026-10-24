package pe.edu.cibertec.audit.messaging.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.cibertec.audit.client.UserClient;
import pe.edu.cibertec.audit.client.dto.UserResponseDto;
import pe.edu.cibertec.audit.entity.AuditRecord;
import pe.edu.cibertec.audit.mapper.AuditMapper;
import pe.edu.cibertec.audit.messaging.event.AuditEvent;
import pe.edu.cibertec.audit.messaging.producer.AuditAlertProducer;
import pe.edu.cibertec.audit.service.AuditService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditConsumerTest {

    @Mock
    private AuditService auditService;
    @Mock
    private AuditMapper auditMapper;
    @Mock
    private UserClient userClient;
    @Mock
    private AuditAlertProducer alertProducer;

    @InjectMocks
    private AuditConsumer consumer;

    @Test
    void persistsTheAuditAndRequestsAnAsynchronousNotification() {
        AuditEvent event = new AuditEvent();
        event.setAssetId("LAP-001");
        event.setUserId("USR-001");
        event.setActionType("ASIGNACION_CREADA");
        AuditRecord record = new AuditRecord();
        UserResponseDto user = new UserResponseDto();
        user.setEmail("usuario@assetflow.pe");
        user.setFullName("Usuario AssetFlow");
        when(auditMapper.toEntity(event)).thenReturn(record);
        when(userClient.getUserById("USR-001")).thenReturn(user);

        consumer.consumeAuditEvent(event);

        verify(auditService).saveAudit(record);
        verify(alertProducer).sendNotification(
                "usuario@assetflow.pe",
                "Movimiento del activo LAP-001",
                "ASIGNACION_CREADA"
        );
    }
}
