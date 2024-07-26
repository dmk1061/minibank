package job.testtask.minibank.service;

import job.testtask.minibank.dto.CreateEmailDto;
import job.testtask.minibank.dto.DeleteEmailDto;
import job.testtask.minibank.dto.UpdateEmailDto;
import job.testtask.minibank.entity.EmailDataEntity;
import job.testtask.minibank.repository.EmailDataRepository;
import job.testtask.minibank.util.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmailDataServiceImpl implements EmailDataService {
    private  final EmailDataRepository emailDataRepository;
    private  final UserUtil userUtil;



    @Override
    public void createEmail(final CreateEmailDto dto) {
        final EmailDataEntity emailDataEntity = new EmailDataEntity();
        emailDataEntity.setEmail(dto.getEmail());
        emailDataEntity.setUser(userUtil.getCurrentUser());
        emailDataRepository.save(emailDataEntity);
        log.info("email created " + dto );
    }

    @Override
    public void deleteEmail(final DeleteEmailDto dto) {
        final EmailDataEntity emailDataEntity = emailDataRepository.getEmailDataEntityByEmail(dto.getEmail());
        emailDataRepository.delete(emailDataEntity);
        log.info("email deleted " + dto );
    }
    @Override
    public void updateEmail(final UpdateEmailDto dto) {
        final EmailDataEntity emailDataEntity = emailDataRepository.getEmailDataEntityByEmail(dto.getOldValue());
        emailDataEntity.setEmail(dto.getNewValue());
        emailDataRepository.save(emailDataEntity);
        log.info("email updated " + dto );
    }
}
