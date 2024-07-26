package job.testtask.minibank.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import job.testtask.minibank.dto.TransferRequestDto;
import job.testtask.minibank.service.TransferService;
import job.testtask.minibank.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;
    private final JwtUtil jwtUtil;
    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody @Valid final TransferRequestDto requestDto,
                                           final HttpServletRequest request) {

        log.info("transfer request received " + requestDto );
        final String jwt = jwtUtil.extractJwtFromRequest(request);
        final String transferFromId = jwtUtil.extractAllClaims(jwt).get(JwtUtil.USER_ID).toString();
        final String result = transferService.transfer(requestDto, transferFromId);
        if (HttpStatus.OK.getReasonPhrase().equals(result)) {
            return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}
