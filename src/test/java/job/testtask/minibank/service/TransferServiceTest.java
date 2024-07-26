package job.testtask.minibank.service;

import job.testtask.minibank.entity.AccountEntity;
import job.testtask.minibank.repository.AccountRepository;
import job.testtask.minibank.repository.EmailDataRepository;
import job.testtask.minibank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private EmailDataRepository emailDataRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private TransferServiceImpl transferService;


    @Test
    public void depositMoney() throws InterruptedException {
        // given - precondition or setup

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setInitialBalance(new BigDecimal(100));
        accountEntity.setBalance(new BigDecimal(100));
        given(accountRepository.saveAndFlush(accountEntity))
                .willReturn(accountEntity);

        // when -  action or the behaviour that we are going test
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int a = 0; a < 100; a++) {
            executorService.execute(() -> transferService.deposit(accountEntity, new BigDecimal(1)));
        }
        Thread.sleep(2000);
        // then - verify the output
        assertThat(accountEntity.getBalance().compareTo(new BigDecimal(200)) == 0);

    }

    @Test
    public void transferMoney() throws InterruptedException {
        // given - precondition or setup

        final AccountEntity transferFrom = new AccountEntity();
        transferFrom.setInitialBalance(new BigDecimal(100));
        transferFrom.setBalance(new BigDecimal(100));
        transferFrom.setId(1L);
        final AccountEntity transferTo = new AccountEntity();
        transferTo.setInitialBalance(new BigDecimal(100));
        transferTo.setBalance(new BigDecimal(100));
        transferTo.setId(2L);
        given(accountRepository.saveAndFlush(transferFrom))
                .willReturn(null);

        // when -  action or the behaviour that we are going test
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int a = 0; a < 50; a++) {
            executorService.execute(() -> transferService.transfer(transferFrom, transferTo, new BigDecimal(1)));
        }
        Thread.sleep(2000);
        // then - verify the output
        assertThat(transferFrom.getBalance().compareTo(new BigDecimal(50)) == 0);
        assertThat(transferFrom.getBalance().compareTo(new BigDecimal(150)) == 0);

    }
}

