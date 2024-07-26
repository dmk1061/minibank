package job.testtask.minibank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeletePhoneDto {
    @NotEmpty
    private String phone;
}
