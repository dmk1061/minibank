package job.testtask.minibank.service;

import job.testtask.minibank.dto.UserSearchDto;
import job.testtask.minibank.entity.EmailDataEntity;
import job.testtask.minibank.entity.PhoneDataEntity;
import job.testtask.minibank.entity.UserEntity;
import job.testtask.minibank.repository.EmailDataRepository;
import job.testtask.minibank.repository.PhoneDataRepository;
import job.testtask.minibank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    protected final UserRepository userRepository;
    private final EmailDataRepository emailDataRepository;
    private final PhoneDataRepository phoneDataRepository;
    public static final  DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public UserEntity getUserNameByEmail(final String email) {
        final EmailDataEntity emailDataEntity = emailDataRepository.getEmailDataEntityByEmail(email);
        return userRepository.getUserEntityByEmail(emailDataEntity);
    }

    public UserEntity getUserNameByPhone(final String phone) {
        final PhoneDataEntity phoneDataEntity = phoneDataRepository.getPhoneDataEntityByPhone(phone);
        return userRepository.getUserEntityByPhone(phoneDataEntity);
    }

    public List<UserEntity> getUsers(final UserSearchDto userSearchDto) {
        log.info("performing request getUsers " + userSearchDto);

        List<UserEntity> result = new ArrayList<>(0);
        switch (userSearchDto.getFilterType()) {

            case NAME:
                result = userRepository.getUserEntitiesByNameLike("%" + userSearchDto.getValue() + "%", PageRequest.of(userSearchDto.getPage(), userSearchDto.getSize()));
                break;
            case EMAIL:
                result = Arrays.asList(userRepository.getUserEntityByEmail(emailDataRepository.getEmailDataEntityByEmail(userSearchDto.getValue())));
                break;
            case PHONE:
                result = Arrays.asList(userRepository.getUserEntityByPhone(phoneDataRepository.getPhoneDataEntityByPhone(userSearchDto.getValue())));
                break;
            case DATE_OF_BIRTH:
                LocalDate date = LocalDate.parse(userSearchDto.getValue(), DATE_TIME_FORMATTER);
                result = userRepository.getUserEntitiesByDateOfBirthAfter(date, PageRequest.of(userSearchDto.getPage(), userSearchDto.getSize()));
                break;
            default:
        }
        return result;
    }

}
