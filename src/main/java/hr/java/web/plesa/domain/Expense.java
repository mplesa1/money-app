package hr.java.web.plesa.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Data
public class Expense {
    private String name;

    private BigDecimal amount;

    private TypeOfExpense typeOfExpense;

    public Expense() {
    }

    public Expense(String name, BigDecimal amount, TypeOfExpense typeOfExpense) {
        this.name = name;
        this.amount = amount;
        this.typeOfExpense = typeOfExpense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TypeOfExpense getTypeOfExpense() {
        return typeOfExpense;
    }

    public void setTypeOfExpense(TypeOfExpense typeOfExpense) {
        this.typeOfExpense = typeOfExpense;
    }

    public static enum TypeOfExpense{
        REŽIJE,
        HRANA,
        PIĆE,
        PRIJEVOZ
    }
}
