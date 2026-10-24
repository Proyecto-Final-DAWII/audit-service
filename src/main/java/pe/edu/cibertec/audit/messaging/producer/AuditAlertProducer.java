package pe.edu.cibertec.audit.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.audit.config.rabbitmq.RabbitMqTemplate;
import pe.edu.cibertec.audit.config.rabbitmq.RabbitProperties;
import pe.edu.cibertec.audit.messaging.event.NotificationEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditAlertProducer {

    private final RabbitMqTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    public void sendNotification(String to, String subject, String body) {
        NotificationEvent event = NotificationEvent.builder()
                .to(to)
                .subject(subject)
                .body(body)
                .build();
        rabbitTemplate.convertAndSend(rabbitProperties.getRoute("notifications"), event);
        log.info("Notificación publicada para {}", to);
    }
}
