package projeto.arenaFuria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.arenaFuria.entity.model.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, String> {
    Optional<Room> findById(String Id);
    Optional<Room> findByName(String name);
}
