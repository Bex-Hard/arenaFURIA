package projeto.arenaFuria.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JoinRoomDTO(@NotBlank @NotNull String roomName) {
}
