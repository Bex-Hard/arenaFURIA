package projeto.arenaFuria.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.arenaFuria.dto.auth.AuthenticationDTO;
import projeto.arenaFuria.dto.auth.LoginResponseDTO;
import projeto.arenaFuria.dto.register.UserRegisterDTO;
import projeto.arenaFuria.dto.register.UserRegisterResponseDTO;
import projeto.arenaFuria.entity.enums.UserRole;
import projeto.arenaFuria.entity.model.User;
import projeto.arenaFuria.infra.security.TokenService;
import projeto.arenaFuria.services.AuthorizationService;


@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();

        var token = tokenService.generateToken(user.getUsername(), data.email());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterDTO data) {
        var userCreated = authorizationService.register(data, UserRole.ADMIN);
        return ResponseEntity.ok(userCreated);
    }
}
