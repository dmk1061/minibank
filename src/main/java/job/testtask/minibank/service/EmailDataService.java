package job.testtask.minibank.service;

import job.testtask.minibank.dto.CreateEmailDto;
import job.testtask.minibank.dto.DeleteEmailDto;
import job.testtask.minibank.dto.UpdateEmailDto;

public interface EmailDataService {

    void createEmail (CreateEmailDto dto);
    void deleteEmail (DeleteEmailDto dto);
    void updateEmail (UpdateEmailDto dto);
}
