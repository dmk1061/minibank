package job.testtask.minibank.service;

import job.testtask.minibank.entity.UserEntity;
import job.testtask.minibank.repository.EmailDataRepository;
import job.testtask.minibank.repository.PhoneDataRepository;
import job.testtask.minibank.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Primary
@Service
public class MyUserDetailService extends UserService implements UserDetailsService {
    public MyUserDetailService(UserRepository userRepository, EmailDataRepository emailDataRepository, PhoneDataRepository phoneDataRepository) {
        super(userRepository, emailDataRepository, phoneDataRepository);
    }

    @Override
    public UserDetails loadUserByUsername( final String name) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.getUserEntityByName(name);
        return new User(userEntity.getName(),userEntity.getPassword(), new ArrayList<>()) {
        };
    }

}
