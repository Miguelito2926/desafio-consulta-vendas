package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.SaleProjection;

public class SaleSummaryDTO {

    private String sellerName;
    private Double total;

    public SaleSummaryDTO() {
    }

    public SaleSummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSummaryDTO(SaleProjection projection) {
        sellerName = projection.getSellerName();
        total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SaleSummaryDTO{" +
                "sellerName='" + sellerName + '\'' +
                ", total=" + total +
                '}';
    }
}
