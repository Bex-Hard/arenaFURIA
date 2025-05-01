package projeto.arenaFuria.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import projeto.arenaFuria.entity.enums.RoomRoles;

public record MemberRegisterDTO( @NotBlank @NotNull String userId,
                                 @NotBlank @NotNull String roomId,
                                 @NotNull RoomRoles roomRole) {
}
