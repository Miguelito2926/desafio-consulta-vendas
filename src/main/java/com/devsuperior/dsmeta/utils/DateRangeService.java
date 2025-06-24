package com.devsuperior.dsmeta.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class DateRangeService {

    public LocalDate[] calculateDateRange(String txtMinDate, String txtMaxDate) {
        LocalDate maxDate = (txtMaxDate == null || txtMaxDate.isEmpty()) ?
                LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) :
                LocalDate.parse(txtMaxDate);

        LocalDate minDate = (txtMinDate == null || txtMinDate.isEmpty()) ?
                maxDate.minusYears(1L) :
                LocalDate.parse(txtMinDate);

        return new LocalDate[]{minDate, maxDate};
    }
}
