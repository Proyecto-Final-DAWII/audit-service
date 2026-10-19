package pe.edu.cibertec.audit.client.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private String role;
}