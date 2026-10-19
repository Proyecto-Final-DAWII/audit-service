package pe.edu.cibertec.audit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.audit.dto.response.AuditResponseDto;
import pe.edu.cibertec.audit.mapper.AuditMapper;
import pe.edu.cibertec.audit.service.AuditService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;
    private final AuditMapper auditMapper;

    /**
     * Obtiene el historial completo de todas las auditorías registradas en el sistema.
     * URL de prueba: GET http://localhost:8083/api/v1/audit/
     */
    @GetMapping
    public ResponseEntity<List<AuditResponseDto>> getAllAudits() {
        var records = auditService.getAllAudits();
        var responseDtos = records.stream()
                .map(auditMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    /**
     * Obtiene el historial de auditoría filtrado por el ID del activo.
     * URL de prueba: GET http://localhost:8083/api/v1/audit/asset/LAP-001
     */
    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AuditResponseDto>> getHistoryByAsset(@PathVariable String assetId) {
        var records = auditService.getHistoryByAsset(assetId);
        var responseDtos = records.stream()
                .map(auditMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }
}