package job.testtask.minibank.util;

import job.testtask.minibank.entity.UserEntity;
import job.testtask.minibank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUtil {
    private  final UserRepository userRepository;
    public UserEntity getCurrentUser () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final UserEntity userEntity = userRepository.getUserEntityByNameCached(currentPrincipalName);
        return userEntity;
    }
}
