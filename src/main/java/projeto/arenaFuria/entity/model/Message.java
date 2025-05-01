package projeto.arenaFuria.entity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id_room")
    private Room roomId;
    @Column
    private String createdAt;
    @Column
    private boolean deleted;

}
