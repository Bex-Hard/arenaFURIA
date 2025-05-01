package projeto.arenaFuria.dto.register;

import java.time.LocalDateTime;
import java.util.List;

public record RoomWithMembersDTO(
        String id,
        String name,
        LocalDateTime createdAt,
        List<MemberRegisterResponseDTO> members
) {}