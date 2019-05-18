package hr.java.web.plesa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private WalletType type;

    @OneToMany(mappedBy = "wallet")
    private List<Expense> expenses;

    private String username;


    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.username = SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public enum WalletType {
        CASH,
        CARD
    }

    public Wallet(LocalDateTime createDate, WalletType type, List<Expense> expenses, String username) {
        this.createDate = createDate;
        this.type = type;
        this.expenses = expenses;
        this.username = username;
    }

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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