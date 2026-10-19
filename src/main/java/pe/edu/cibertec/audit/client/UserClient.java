package pe.edu.cibertec.audit.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.audit.client.dto.UserResponseDto;
import pe.edu.cibertec.audit.config.openfeign.OpenFeignConfiguration; // Importa tu configuración existente

// "identity-service" es el nombre con el que el otro microservicio se registra en Eureka
@FeignClient(name = "identity-service", configuration = OpenFeignConfiguration.class)
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}")
    UserResponseDto getUserById(@PathVariable("userId") String userId);
}