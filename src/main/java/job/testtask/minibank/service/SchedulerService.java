package job.testtask.minibank.service;

import job.testtask.minibank.entity.AccountEntity;
import job.testtask.minibank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {
    private  final AccountRepository accountRepository;

    private final  TransferService transferService;



    @Scheduled(cron ="*/30 * * * * *")
    public void moneyIncrease () {
        log.info("deposit update scheduler launched at " + LocalDateTime.now());
        final List<AccountEntity> accountEntities = accountRepository.findAll();
        accountEntities.forEach(this :: checkAndAddMoney);
    }

    private void checkAndAddMoney (final AccountEntity accountEntity) {
        final BigDecimal hundred = new BigDecimal(100);
        final BigDecimal pct = new BigDecimal(10);
        final BigDecimal limit = new BigDecimal(207);
        final BigDecimal expected = accountEntity.getBalance().add(accountEntity.getBalance().divide(pct, RoundingMode.HALF_UP));
        final BigDecimal limited = accountEntity.getInitialBalance().divide(hundred, RoundingMode.HALF_UP).multiply(limit);
        if (limited.compareTo(expected)>0){
            transferService.deposit(accountEntity, expected.subtract(accountEntity.getBalance()));
        }
    }
}
