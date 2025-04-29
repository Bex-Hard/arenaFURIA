package projeto.arenaFuria.services;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.arenaFuria.dto.user.UpdateUserResponseDTO;
import projeto.arenaFuria.entity.model.User;
import projeto.arenaFuria.infra.security.TokenService;
import projeto.arenaFuria.repository.UserRepository;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public UserDetails getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found"));
    }
    public UpdateUserResponseDTO updatePassword (String email, String oldPassword, String newPassword){
        var authToken = new UsernamePasswordAuthenticationToken(email, oldPassword);
        try {
            var auth = this.authenticationManager.authenticate(authToken);
            User user = (User) auth.getPrincipal();
            System.out.println("chegou aqui");
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userRepository.save(user);

            return new UpdateUserResponseDTO(user.getEmail(), "Senha atualizada com sucesso.");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Senha antiga inválida.");
        }
    }
    public UpdateUserResponseDTO deleteUser (String email, String password, String token) throws AccessDeniedException {
        var existUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        String senderUserName = tokenService.extractUsername(token);

        if (!senderUserName.equalsIgnoreCase(existUser.getUsername())) {
            throw new AccessDeniedException("Você só pode deletar sua própria conta.");
        }

        if (!passwordEncoder.matches(password, existUser.getPassword())) {
            throw new BadCredentialsException("Senha incorreta.");
        }

        try {
            User user = (User) existUser;
            userRepository.delete(user);

            return new UpdateUserResponseDTO(user.getEmail(), "Usuário deletado com sucesso.");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Email ou senha inválidos. Usuário não foi deletado.");
        }
    }
}
