package job.testtask.minibank.dto;

import lombok.Value;

@Value
public class AuthenticationResponse {

    private final String jwt;

}
