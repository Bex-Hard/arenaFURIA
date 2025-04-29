package projeto.arenaFuria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import projeto.arenaFuria.dto.auth.AuthenticationDTO;
import projeto.arenaFuria.dto.register.UserRegisterDTO;
import projeto.arenaFuria.dto.register.UserRegisterResponseDTO;
import projeto.arenaFuria.dto.user.UpdateUserDTO;
import projeto.arenaFuria.dto.user.UpdateUserResponseDTO;
import projeto.arenaFuria.entity.enums.UserRole;
import projeto.arenaFuria.infra.security.TokenService;
import projeto.arenaFuria.services.AuthorizationService;
import projeto.arenaFuria.services.UserService;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterDTO data){
        var userCreated = authorizationService.register(data, UserRole.USER);
        return ResponseEntity.ok(userCreated);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDetails> getUserByEmail(@PathVariable String email) {
        UserDetails user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update-password")
    public ResponseEntity<UpdateUserResponseDTO> updatePassword(@RequestBody UpdateUserDTO data) {
        UpdateUserResponseDTO response = userService.updatePassword(
                data.email(), data.oldPassword(), data.newPassword()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<UpdateUserResponseDTO> deleteUser (@RequestBody AuthenticationDTO data, @RequestHeader("Authorization") String authorizationHeader) throws AccessDeniedException {
        String token = authorizationHeader.replace("Bearer ", "");
        UpdateUserResponseDTO response = userService.deleteUser(
                data.email(), data.password(), token);
        return ResponseEntity.ok(response);
    }
}
