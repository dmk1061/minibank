package job.testtask.minibank.repository;

import job.testtask.minibank.entity.UserEntity;
import job.testtask.minibank.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
        final PageRequest pageRequest = PageRequest.of(0,10);
        final LocalDate date = LocalDate.parse("05.01.1994", UserService.DATE_TIME_FORMATTER);
        final List<UserEntity> userEntities= userRepository.getUserEntitiesByDateOfBirthAfter(date, pageRequest);
        Assertions.assertEquals(userEntities.size(),2);
    }


}
