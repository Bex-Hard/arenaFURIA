package projeto.arenaFuria.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import projeto.arenaFuria.dto.auth.AuthenticationDTO;
import projeto.arenaFuria.dto.register.UserRegisterDTO;
import projeto.arenaFuria.dto.register.UserRegisterResponseDTO;
import projeto.arenaFuria.dto.user.UpdateUserDTO;
import projeto.arenaFuria.dto.user.UpdateUserResponseDTO;
import projeto.arenaFuria.entity.enums.UserRole;
import projeto.arenaFuria.entity.model.User;
import projeto.arenaFuria.services.AuthorizationService;
import projeto.arenaFuria.services.UserService;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@SecurityRequirement(name = "JWT")
public class UserController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário com permissões de USER")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterDTO data){
        var userCreated = authorizationService.register(data, UserRole.USER);
        return ResponseEntity.ok(userCreated);
    }

    @GetMapping("/{email}")
    @Operation(
        summary = "Buscar usuário por email",
        description = "Retorna os detalhes do usuário pelo email. Requer autenticação."
    )
    public ResponseEntity<UserDetails> getUserByEmail(@PathVariable String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        
        if (!authenticatedUser.getEmail().equals(email) && 
            !authenticatedUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new SecurityException("Você só pode visualizar seus próprios dados, a menos que seja um administrador.");
        }
        
        UserDetails user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update-password")
    @Operation(summary = "Atualizar senha", description = "Atualiza a senha do usuário")
    public ResponseEntity<UpdateUserResponseDTO> updatePassword(@RequestBody UpdateUserDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        
        if (!authenticatedUser.getEmail().equals(data.email())) {
            throw new SecurityException("Você só pode alterar sua própria senha.");
        }
        
        UpdateUserResponseDTO response = userService.updatePassword(
                data.email(), data.oldPassword(), data.newPassword()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(summary = "Deletar usuário", description = "Remove o usuário do sistema")
    public ResponseEntity<UpdateUserResponseDTO> deleteUser(@RequestBody AuthenticationDTO data) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        
        UpdateUserResponseDTO response = userService.deleteUser(
                data.email(), data.password(), authenticatedUser.getEmail());
        return ResponseEntity.ok(response);
    }
}
