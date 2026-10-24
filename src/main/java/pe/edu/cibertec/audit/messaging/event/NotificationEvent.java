package pe.edu.cibertec.audit.messaging.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pe.edu.cibertec.audit.config.rabbitmq.RabbitMqDto;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class NotificationEvent extends RabbitMqDto<Void> {
    private String to;
    private String subject;
    private String body;
}
