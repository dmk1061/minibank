package job.testtask.minibank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeleteEmailDto {
    @NotEmpty
    private String email;
}
