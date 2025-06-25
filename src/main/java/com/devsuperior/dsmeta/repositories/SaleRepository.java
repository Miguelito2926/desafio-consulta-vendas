package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

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

    @Query("""
    SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(seller.name, SUM(s.amount))
    FROM Sale s
    JOIN s.seller seller
    WHERE s.date BETWEEN :minDate AND :maxDate
    GROUP BY seller.name
    ORDER BY SUM(s.amount) DESC
""")
    List<SaleSummaryDTO> getSalesAmountGroupedBySeller(LocalDate minDate, LocalDate maxDate);
}
