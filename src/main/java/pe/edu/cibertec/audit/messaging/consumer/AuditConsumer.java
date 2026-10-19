package pe.edu.cibertec.audit.messaging.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.audit.messaging.event.AuditEvent;
import pe.edu.cibertec.audit.service.AuditService;
import pe.edu.cibertec.audit.mapper.AuditMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditConsumer {

    private final AuditService auditService;
    private final AuditMapper auditMapper;

    @RabbitListener(queues = "${rabbitmq.queue.audit:audit-events-queue}")
    public void consumeAuditEvent(AuditEvent event) {
        log.info("Evento de auditoría recibido vía RabbitMQ para el activo: {}", event.getAssetId());
        // El mapper convierte el Evento en Entidad y el servicio lo guarda en MySQL
        auditService.saveAudit(auditMapper.toEntity(event));
    }
}