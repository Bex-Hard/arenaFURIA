package projeto.arenaFuria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.arenaFuria.entity.model.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByUserId_IdAndRoomId_Id(String userId, String roomId);
    void deleteByRoomId_Id(String roomId);
}
