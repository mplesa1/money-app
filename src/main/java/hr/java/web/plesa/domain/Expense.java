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

    @NotEmpty(message = "Niste unijeli naziv troška.")
    @Size(min = 2, max = 35, message = "Naziv mora imati između 2 i 35 znakova.")
    private String name;

    @NotNull(message = "Niste unijeli iznos.")
    @DecimalMin(value = "0.01", message = "Minimalan iznos je 0.01.")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Odaberite vrstu troška. Smjesta!")
    private ExpenseType expenseType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;


    public enum ExpenseType {

        FOOD,
        DRINKS,
        WASTING_MONEY_ON_STUFF_YOU_DONT_NEED

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