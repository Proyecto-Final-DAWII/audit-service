package pe.edu.cibertec.audit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.audit.entity.AuditRecord;
import pe.edu.cibertec.audit.repository.AuditRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditRecord saveAudit(AuditRecord record) {
        return auditRepository.save(record);
    }

    /**
     * Obtiene el historial completo de todas las auditorías registradas.
     */
    public List<AuditRecord> getAllAudits() {
        return auditRepository.findAll();
    }

    public List<AuditRecord> getHistoryByAsset(String assetId) {
        return auditRepository.findByAssetIdOrderByTimestampDesc(assetId);
    }

}