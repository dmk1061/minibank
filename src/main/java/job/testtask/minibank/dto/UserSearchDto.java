package job.testtask.minibank.dto;

import jakarta.validation.constraints.NotEmpty;
import job.testtask.minibank.filter.FILTER_TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSearchDto {

    @NotEmpty
    String value;
    @NotEmpty
    FILTER_TYPE filterType;
    @NotEmpty
    int size;
    @NotEmpty
    int page;
}
