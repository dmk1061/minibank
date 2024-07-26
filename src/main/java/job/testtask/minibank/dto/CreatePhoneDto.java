package job.testtask.minibank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreatePhoneDto {
    @NotEmpty
    private String phone;
}
