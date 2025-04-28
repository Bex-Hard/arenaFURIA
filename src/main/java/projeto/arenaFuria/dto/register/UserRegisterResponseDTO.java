package projeto.arenaFuria.dto.register;

import lombok.Builder;

@Builder
public record UserRegisterResponseDTO(String username, String email) {
}
