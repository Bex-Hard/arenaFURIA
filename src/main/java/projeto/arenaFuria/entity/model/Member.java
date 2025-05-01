package projeto.arenaFuria.entity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import projeto.arenaFuria.entity.enums.RoomRoles;
import projeto.arenaFuria.entity.enums.UserRole;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="members")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 255, name = "id_member")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id_room")
    private Room roomId;

    @Column(name = "type_member", length = 50)
    private RoomRoles roomRole;

    @Column
    private LocalDateTime joinedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roomRole == RoomRoles.OWNER){
            return List.of(new SimpleGrantedAuthority("ROLE_OWNER"),
                    new SimpleGrantedAuthority("ROLE_MEMBER"));
        } else return  List.of(new SimpleGrantedAuthority("ROLE_MEMBER"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return userId.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
