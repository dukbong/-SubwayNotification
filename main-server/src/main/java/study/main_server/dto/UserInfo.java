package study.main_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter @Setter
public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -45326L;

    @NotBlank(message = "Email cannot be null")
    private String email;
}
