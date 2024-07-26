package job.testtask.minibank.service;


import job.testtask.minibank.dto.CreatePhoneDto;
import job.testtask.minibank.dto.DeletePhoneDto;
import job.testtask.minibank.dto.UpdatePhoneDto;
import job.testtask.minibank.entity.PhoneDataEntity;
import job.testtask.minibank.repository.PhoneDataRepository;
import job.testtask.minibank.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneDataServiceImpl implements PhoneDataService {
    private final UserUtil userUtil;
    private final PhoneDataRepository phoneDataRepository;

    @Override
    public void createPhone(final CreatePhoneDto dto) {
        final PhoneDataEntity phoneDataEntity = new PhoneDataEntity();
        phoneDataEntity.setPhone(dto.getPhone());
        phoneDataEntity.setUser(userUtil.getCurrentUser());
        phoneDataRepository.save(phoneDataEntity);
        log.info("phone created " + dto );
    }

    @Override
    public void deletePhone(final DeletePhoneDto dto) {
       final PhoneDataEntity phoneDataEntity = phoneDataRepository.getPhoneDataEntityByPhone(dto.getPhone());
       phoneDataRepository.delete(phoneDataEntity);
        log.info("phone deleted " + dto );
    }

    @Override
    public void updatePhone(final UpdatePhoneDto dto) {
        final PhoneDataEntity phoneDataEntity = phoneDataRepository.getPhoneDataEntityByPhone(dto.getOldValue());
        phoneDataEntity.setPhone(dto.getNewValue());
        phoneDataRepository.save(phoneDataEntity);
        log.info("phone updated " + dto );
    }
}
