package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query("""
    SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(s.id, s.date, s.amount, se.name)
    FROM Sale s
    JOIN s.seller se
    WHERE s.date BETWEEN :minDate AND :maxDate
    AND LOWER(se.name) LIKE LOWER(CONCAT('%', :name, '%'))
    ORDER BY s.date DESC
""")
    Page<SaleReportDTO> searchReport(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
