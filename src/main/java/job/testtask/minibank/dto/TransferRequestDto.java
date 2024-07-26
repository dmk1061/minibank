package job.testtask.minibank.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequestDto {

  @NotEmpty
  private String transferTo;
  @NotNull
  private BigDecimal amount;


}
