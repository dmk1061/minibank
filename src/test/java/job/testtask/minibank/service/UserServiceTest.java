package job.testtask.minibank.service;


import job.testtask.minibank.dto.UserSearchDto;
import job.testtask.minibank.entity.EmailDataEntity;
import job.testtask.minibank.entity.UserEntity;
import job.testtask.minibank.filter.FILTER_TYPE;
import job.testtask.minibank.repository.EmailDataRepository;
import job.testtask.minibank.repository.PhoneDataRepository;
import job.testtask.minibank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    protected  UserRepository userRepository;
    @Mock
    private EmailDataRepository emailDataRepository;
    @Mock
    private  PhoneDataRepository phoneDataRepository;
    @InjectMocks
    private  UserService userService;

    @Test
    public void getUsers(){
        // given - precondition or setup

        final UserEntity userEntity = new UserEntity();
                userEntity.setId(1L);
                userEntity.setDateOfBirth(LocalDate.parse("11.11.1956",UserService.DATE_TIME_FORMATTER));
                EmailDataEntity emailDataEntity = new EmailDataEntity();
                emailDataEntity.setEmail("user1@mail.ru");
                userEntity.setEmail(List.of(emailDataEntity));
        final UserEntity userEntity2 = new UserEntity();
        userEntity2.setDateOfBirth(LocalDate.parse("11.11.1976",UserService.DATE_TIME_FORMATTER));
        userEntity2.setId(2L);
        final EmailDataEntity emailDataEntity2 = new EmailDataEntity();
        emailDataEntity2.setEmail("user2@mail.ru");
        userEntity2.setEmail(List.of(emailDataEntity2));

        given(userRepository.getUserEntitiesByDateOfBirthAfter(LocalDate.parse("11.11.1945", UserService.DATE_TIME_FORMATTER), PageRequest.of(0,10)))
                .willReturn(List.of(userEntity,userEntity2));

        // when -  action or the behaviour that we are going test
        final UserSearchDto userSearchDto = new UserSearchDto("11.11.1945", FILTER_TYPE.DATE_OF_BIRTH, 10,0);
        final List<UserEntity> employeeList = userService.getUsers(userSearchDto);

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

}
