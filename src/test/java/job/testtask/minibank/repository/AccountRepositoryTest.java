package job.testtask.minibank.repository;

import job.testtask.minibank.entity.AccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest extends AbstractContainerBaseTest {
    @Autowired
    AccountRepository accountRepository;


    @Test
    public void saveToDb() {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(new BigDecimal(100));
        accountEntity.setInitialBalance(new BigDecimal(100));
        accountRepository.save(accountEntity);
        final AccountEntity savedEntity = accountRepository.findById(accountEntity.getId()).get();

        assertThat(savedEntity).isNotNull();
    }
}
