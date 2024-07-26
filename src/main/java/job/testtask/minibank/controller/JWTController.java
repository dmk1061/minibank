package job.testtask.minibank.controller;

import jakarta.validation.Valid;
import job.testtask.minibank.dto.AuthenticationRequest;
import job.testtask.minibank.dto.AuthenticationResponse;
import job.testtask.minibank.entity.UserEntity;
import job.testtask.minibank.service.MyUserDetailService;
import job.testtask.minibank.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class JWTController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/getToken")
    public ResponseEntity getToken(@RequestBody @Valid final AuthenticationRequest authenticationRequest) {
        log.info("token request received " + authenticationRequest );
        UserEntity user;
        if (authenticationRequest.getEmail() != null) {
            user = userDetailsService.getUserNameByEmail(authenticationRequest.getEmail());
        } else if (authenticationRequest.getPhone() != null) {
            user = userDetailsService.getUserNameByPhone(authenticationRequest.getPhone());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email or phone  wasn't provided in request");
        }
        try {
            final Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
            final String jwt = jwtUtil.generateToken(userDetails, user.getId().toString());
            log.info("token request comleted " + authenticationRequest + "--> " +jwt  );
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (NullPointerException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad credentials");
        }
    }
}




