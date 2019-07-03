package hr.java.web.plesa.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense extends BaseEntity {

    @NotEmpty(message = "{validation.expense.name.notEmpty}.")
    @Size(min = 2, max = 35, message = "{validation.expense.name.size}.")
    private String name;

    @NotNull(message = "{validation.expense.amount.notNull}.")
    @DecimalMin(value = "0.01", message = "{validation.expense.amount.min}.")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{validation.expense.expenseType}")
    private ExpenseType expenseType;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{validation.expense.placeOfExpense}")
    private PlaceOfExpense placeOfExpense;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;


    public enum ExpenseType {

        FOOD,
        DRINKS,
        WASTING_MONEY_ON_STUFF_YOU_DONT_NEED

    }

    public enum PlaceOfExpense {
        ZAGREB,
        RIJEKA,
        GORICA
    }

    @Override
    public String toString() {
        return "Expense{" +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", expenseType=" + expenseType +
                ", wallet=" + wallet +
                '}';
    }
}