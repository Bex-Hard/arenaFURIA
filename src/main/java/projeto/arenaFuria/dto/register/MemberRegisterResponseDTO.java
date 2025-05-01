package projeto.arenaFuria.dto.register;

import lombok.Builder;
import lombok.Data;
import projeto.arenaFuria.entity.enums.RoomRoles;

import java.time.LocalDateTime;

@Builder
public record MemberRegisterResponseDTO(String id,
                                        String roomId,
                                        String email,
                                        RoomRoles roomRole,
                                        LocalDateTime joinedAt) {
}
