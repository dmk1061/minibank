package job.testtask.minibank.service;


import job.testtask.minibank.dto.TransferRequestDto;
import job.testtask.minibank.entity.AccountEntity;
import java.math.BigDecimal;

public interface TransferService {
    String transfer (TransferRequestDto transferRequestDto, String transferFrom);
    void deposit (AccountEntity transferTo, BigDecimal amount);

}
