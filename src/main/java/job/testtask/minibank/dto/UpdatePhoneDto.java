package job.testtask.minibank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdatePhoneDto {

    @NotEmpty
    private String oldValue;
    @NotEmpty
    private String newValue;
}
