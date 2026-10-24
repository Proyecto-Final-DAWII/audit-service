package pe.edu.cibertec.audit.messaging.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.audit.messaging.event.AuditEvent;
import pe.edu.cibertec.audit.service.AuditService;
import pe.edu.cibertec.audit.mapper.AuditMapper;
import pe.edu.cibertec.audit.client.UserClient;
import pe.edu.cibertec.audit.messaging.producer.AuditAlertProducer;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditConsumer {

    private final AuditService auditService;
    private final AuditMapper auditMapper;
    private final UserClient userClient;
    private final AuditAlertProducer alertProducer;

    @RabbitListener(queues = "${app.rabbitmq.routes.audit.queue:assetflow.audit.queue}")
    public void consumeAuditEvent(AuditEvent event) {
        log.info("Evento de auditoría recibido vía RabbitMQ para el activo: {}", event.getAssetId());
        auditService.saveAudit(auditMapper.toEntity(event));

        if (event.getUserId() == null || event.getUserId().isBlank() || "system".equals(event.getUserId())) {
            return;
        }
        try {
            var user = userClient.getUserById(event.getUserId());
            alertProducer.sendNotification(
                    user.getEmail(),
                    "Movimiento del activo " + event.getAssetId(),
                    event.getActionType()
            );
        } catch (Exception exception) {
            log.warn("La auditoría se guardó, pero no se pudo notificar al usuario {}", event.getUserId());
        }
    }
}
