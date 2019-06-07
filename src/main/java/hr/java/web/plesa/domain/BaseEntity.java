package hr.java.web.plesa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private LocalDateTime created;
//    private LocalDateTime modified;

    public boolean isNew() {
        return this.id == null;
    }

//    @PrePersist
//    @PreUpdate
//    public void updateTimeStamps() {
//        modified = LocalDateTime.now();
//        if (created == null) {
//            created = LocalDateTime.now();
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return this.id != null && Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}