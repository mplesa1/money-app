package hr.java.web.plesa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Authorities {
    private String username;

    private Authority authority;

    public static enum Authority {
        ROLE_ADMIN,
        ROLE_USER
    }

}
