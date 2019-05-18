//package hr.java.web.plesa.domain;
//
//        import lombok.Getter;
//        import lombok.NoArgsConstructor;
//        import lombok.Setter;
//        import lombok.ToString;
//
//        import javax.persistence.*;
//        import java.time.LocalDateTime;
//        import java.util.HashSet;
//        import java.util.Set;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@ToString(exclude = "users")
//public class Role {
//
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();
//    private LocalDateTime createDate;
//
//}
