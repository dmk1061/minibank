package job.testtask.minibank.controller;

import jakarta.validation.Valid;
import job.testtask.minibank.dto.CreateEmailDto;
import job.testtask.minibank.dto.UpdateEmailDto;
import job.testtask.minibank.dto.DeleteEmailDto;
import job.testtask.minibank.dto.CreatePhoneDto;
import job.testtask.minibank.dto.UpdatePhoneDto;
import job.testtask.minibank.dto.DeletePhoneDto;
import job.testtask.minibank.service.EmailDataService;
import job.testtask.minibank.service.PhoneDataService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/userData")
@AllArgsConstructor
public class UserDataController {
    //TODO Нужно сделать тесты на покрытие функционала трансфера денег и какую-то API операцию покрыть через testcontainers с использованием MockMvc.

    private  final EmailDataService emailDataService;
    private  final PhoneDataService phoneDataService;


    @PostMapping("/createEmail")
    public ResponseEntity<String> createEmailForCurrentUser(@RequestBody @Valid final CreateEmailDto createEmailDto) {
        emailDataService.createEmail(createEmailDto);
        return ResponseEntity.ok(HttpStatus.OK.name());
    }

    @CacheEvict(value = "emails", key = "#deleteEmailDto.email")
    @DeleteMapping("/deleteEmail")
    public ResponseEntity<String> deleteEmailForCurrentUser(@RequestBody @Valid final DeleteEmailDto deleteEmailDto) {
        emailDataService.deleteEmail(deleteEmailDto);
        return ResponseEntity.ok(HttpStatus.OK.name());
    }

    @PutMapping("/updateEmail")
    public ResponseEntity<String> updateEmailForCurrentUser(@RequestBody @Valid final UpdateEmailDto updateEmailDto) {
        emailDataService.updateEmail(updateEmailDto);
        return ResponseEntity.ok(HttpStatus.OK.name());
    }

    @PostMapping("/createPhone")
    public ResponseEntity<String> createPhoneForCurrentUser(@RequestBody @Valid final CreatePhoneDto createPhoneDto) {
        phoneDataService.createPhone(createPhoneDto);
        return ResponseEntity.ok(HttpStatus.OK.name());
    }
    @CacheEvict(value = "phones", key = "#deletePhoneDto.phone")
    @DeleteMapping("/deletePhone")
    public ResponseEntity<String> deletePhoneForCurrentUser(@RequestBody @Valid final DeletePhoneDto deletePhoneDto) {
        phoneDataService.deletePhone(deletePhoneDto);
        return ResponseEntity.ok(HttpStatus.OK.name());
    }

    @PutMapping("/updatePhone")
    public ResponseEntity<String> updatePhoneForCurrentUser(@RequestBody @Valid final UpdatePhoneDto updatePhoneDto) {
        phoneDataService.updatePhone(updatePhoneDto);
        return ResponseEntity.ok(HttpStatus.OK.name());
    }
}
