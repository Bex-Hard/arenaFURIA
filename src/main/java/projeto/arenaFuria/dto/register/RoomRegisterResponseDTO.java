package projeto.arenaFuria.dto.register;

import java.time.LocalDateTime;

public record RoomRegisterResponseDTO(String id,
                                      String name,
                                      LocalDateTime createdAt) {
}
