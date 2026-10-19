package pe.edu.cibertec.audit.repository;

import pe.edu.cibertec.audit.entity.AuditRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<AuditRecord, Long> {
    List<AuditRecord> findByAssetIdOrderByTimestampDesc(String assetId);
}