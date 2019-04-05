package hr.java.web.plesa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class ExpenseStatistic {
    private String name;
    private BigDecimal totalAmount;
    private BigDecimal maxAmount;
    private BigDecimal avgAmount;

    public ExpenseStatistic(String name, BigDecimal totalAmount, BigDecimal maxAmount, BigDecimal avgAmount) {
        this.name = name;
        this.totalAmount = totalAmount;
        this.maxAmount = maxAmount;
        this.avgAmount = avgAmount;
    }
}
