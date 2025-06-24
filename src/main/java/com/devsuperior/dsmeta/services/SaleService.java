package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.utils.DateRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Autowired
	private DateRangeService dateRangeService;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getReport(String name, String txtMinDate, String txtMaxDate, Pageable pageable) {

		LocalDate[] dates = dateRangeService.calculateDateRange(txtMinDate, txtMaxDate);
		return repository.searchReport(name, dates[0], dates[1], pageable);
	}
}
