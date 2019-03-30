package hr.java.web.plesa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Expense {
    @NotEmpty(message = "Unesite naziv troška.")
    @Size(min = 2, max = 40, message = "Naziv mora imati između 2 i 40 znakova.")
    private String name;

    @NotNull(message = "Unesite iznos.")
    @DecimalMin(value = "0.01", message = "Minimalan iznos je 0.01.")
    private BigDecimal amount;

    @NotNull(message = "Odaberite vrstu troška.")
    private TypeOfExpense typeOfExpense;

    public static enum TypeOfExpense{
        REŽIJE,
        HRANA,
        PIĆE,
        PRIJEVOZ
    }
}
