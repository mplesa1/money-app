package hr.java.web.plesa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Wallet extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private WalletType type;

    @OneToMany(mappedBy = "wallet")
    private List<Expense> expenses;

    private String username;


    public enum WalletType {
        CASH,
        CARD
    }

    public Wallet(WalletType type, List<Expense> expenses, String username) {
        this.type = type;
        this.expenses = expenses;
        this.username = username;
    }

    public Wallet() {
    }

    public WalletType getType() {
        return type;
    }

    public void setType(WalletType type) {
        this.type = type;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}