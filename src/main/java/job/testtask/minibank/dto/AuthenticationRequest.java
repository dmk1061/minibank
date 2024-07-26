package job.testtask.minibank.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;
import lombok.Value;

@Value
public class AuthenticationRequest {

    @NotNull
    private String email;

    @NotNull
    private String phone;


    @ToString.Exclude
    @NotEmpty(message = "password cant be empty")
    private String password;

}
