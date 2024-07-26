package job.testtask.minibank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class CreateEmailDto {
    @NotEmpty
    private String email;
}
