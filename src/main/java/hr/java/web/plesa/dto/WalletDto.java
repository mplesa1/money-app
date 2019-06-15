package hr.java.web.plesa.dto;

import hr.java.web.plesa.domain.BaseEntity;
import hr.java.web.plesa.domain.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto extends BaseEntity{
    private String type;

    private String username;
}