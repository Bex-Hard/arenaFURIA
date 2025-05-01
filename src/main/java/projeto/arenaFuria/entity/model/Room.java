package projeto.arenaFuria.entity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 255, name = "id_room")
    private String id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "roomId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
        member.setRoomId(this);
    }

    public void removeMember(Member member) {
        members.remove(member);
        member.setRoomId(null);
    }
}
