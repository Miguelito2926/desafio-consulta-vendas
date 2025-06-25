package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryDTO>> getSummary(
			@RequestParam(name = "minDate", required = false) String txtMinDate,
			@RequestParam(name = "maxDate", required = false) String txtMaxDate) {

		return ResponseEntity.ok(service.getSummary(txtMinDate, txtMaxDate));
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
			Pageable pageable,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "minDate", required = false) String txtMinDate,
			@RequestParam(name = "maxDate", required = false) String txtMaxDate) {

		return ResponseEntity.ok(service.getReport(name, txtMinDate, txtMaxDate,pageable));
	}
}
