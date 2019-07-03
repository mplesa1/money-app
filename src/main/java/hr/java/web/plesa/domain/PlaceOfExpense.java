package hr.java.web.plesa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlaceOfExpense extends BaseEntity{
    @NotEmpty(message = "Popunite naziv mjesta")
    @Size(min = 2, max = 35, message = "prekratko")
    private String name;
}
