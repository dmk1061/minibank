package job.testtask.minibank.service;

import job.testtask.minibank.dto.CreatePhoneDto;
import job.testtask.minibank.dto.UpdatePhoneDto;
import job.testtask.minibank.dto.DeletePhoneDto;

public interface PhoneDataService {

    void createPhone (CreatePhoneDto dto);
    void deletePhone (DeletePhoneDto dto);
    void updatePhone (UpdatePhoneDto dto);
}
