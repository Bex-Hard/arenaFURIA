package projeto.arenaFuria.entity.enums;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;

@Getter
public enum RoomRoles {
    OWNER("ROLE_OWNER"),
    MEMBER("ROLE_MEMBER");

    private final String value;

    private RoomRoles(String value) {
        this.value = value;
    }
}
