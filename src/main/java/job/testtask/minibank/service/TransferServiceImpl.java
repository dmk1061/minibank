package job.testtask.minibank.service;

import jakarta.transaction.Transactional;
import job.testtask.minibank.dto.TransferRequestDto;
import job.testtask.minibank.entity.AccountEntity;
import job.testtask.minibank.entity.EmailDataEntity;
import job.testtask.minibank.repository.AccountRepository;
import job.testtask.minibank.repository.EmailDataRepository;
import job.testtask.minibank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final UserRepository userRepository;
    private final EmailDataRepository emailDataRepository;
    private final AccountRepository accountRepository;
    private final Random random = new Random();

    @Override //TODO optional rep ?
    @Transactional
    public String transfer(final TransferRequestDto transferRequestDto, final String transferFromId) {
        final EmailDataEntity emailTo = emailDataRepository.getEmailDataEntityByEmail(transferRequestDto.getTransferTo());
        if(emailTo == null) {
            return "cant find user with such email";
        }
        final AccountEntity transferTo = userRepository.getUserEntityByEmail(emailTo).getAccount();
        final AccountEntity transferFrom = userRepository.findById(Long.valueOf(transferFromId)).get().getAccount();
        if (transferFrom.getBalance().compareTo(transferRequestDto.getAmount()) >= 0) {
            transfer(transferFrom, transferTo, transferRequestDto.getAmount());
        } else {
            return "Not enough money on your account for this transaction";
        }
        return HttpStatus.OK.getReasonPhrase();
    }

    @Override
    @Transactional
    public void deposit(final AccountEntity transferTo, final BigDecimal amount) {
        while (true) {
            if (transferTo.getLock().tryLock()) {
                try {
                    transferDB(null, transferTo, amount, true);
                    break;
                } finally {
                    transferTo.getLock().unlock();
                }
            }
        }
    }
    
    
    public void transfer(final AccountEntity transferFrom, final AccountEntity transferTo,
                         final BigDecimal amount) {
        final AccountEntity lockFirst;
        final AccountEntity lockSecond;
        if (transferFrom.getId() < transferTo.getId()) {
            lockFirst = transferFrom;
            lockSecond = transferTo;
        } else {
            lockFirst = transferTo;
            lockSecond = transferFrom;
        }

        while (true) {
            if (lockFirst.getLock().tryLock()) {
                try {
                    if (lockSecond.getLock().tryLock()) {
                        try {
                            transferDB(transferFrom, transferTo, amount, false);
                            break;
                        } finally {
                            lockSecond.getLock().unlock();
                        }
                    }
                } finally {
                    lockFirst.getLock().unlock();
                }
                final int n = random.nextInt(500);
                try {
                    Thread.sleep(1000+n); // 1 second + random delay to prevent livelock
                } catch (InterruptedException e) {

                }
            }
        }
    }

 
    private void transferDB(final AccountEntity transferFrom, final AccountEntity transferTo,
                            final BigDecimal amount, final boolean schedulerDeposit) {
        final BigDecimal rounded = amount.setScale(2, RoundingMode.UP);
        log.info("performing deposit changes " + transferFrom + " - >" +transferTo + " :" + amount );
        if (!schedulerDeposit) {
            transferFrom.setBalance(transferFrom.getBalance().subtract(rounded));
            accountRepository.saveAndFlush(transferFrom);
        }
        transferTo.setBalance(transferTo.getBalance().add(rounded));
        accountRepository.saveAndFlush(transferTo);
    }
}
