package pe.edu.cibertec.audit.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditAlertProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendSecurityAlert(String assetId, String message) {
        log.warn("Enviando alerta de seguridad para el activo: {}", assetId);
        // Envía un mensaje a otro microservicio (ej. Notification Service)
        rabbitTemplate.convertAndSend("exchange-alertas", "routing-key-alerta", message);
    }
}