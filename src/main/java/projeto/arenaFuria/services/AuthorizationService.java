package projeto.arenaFuria.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.arenaFuria.dto.register.UserRegisterDTO;
import projeto.arenaFuria.dto.register.UserRegisterResponseDTO;
import projeto.arenaFuria.entity.enums.UserRole;
import projeto.arenaFuria.entity.model.User;
import projeto.arenaFuria.mapper.UserMapper;
import projeto.arenaFuria.repository.UserRepository;

@Service
@AllArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("Not found"));
    }

    public UserRegisterResponseDTO register(UserRegisterDTO data, UserRole userRole ){
        var existUser = userRepository.findByEmail(data.email());

        if (existUser.isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), data.email(), encryptedPassword, userRole);

        return UserMapper.createUserResgisterDTO(userRepository.save(newUser));
    }
}
