package projeto.arenaFuria.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.arenaFuria.dto.register.MemberRegisterDTO;
import projeto.arenaFuria.dto.register.ResultResponseDTO;
import projeto.arenaFuria.dto.register.RoomRegisterDTO;
import projeto.arenaFuria.dto.register.RoomRegisterResponseDTO;
import projeto.arenaFuria.entity.enums.RoomRoles;
import projeto.arenaFuria.entity.enums.UserRole;
import projeto.arenaFuria.entity.model.Member;
import projeto.arenaFuria.entity.model.Room;
import projeto.arenaFuria.entity.model.User;
import projeto.arenaFuria.mapper.RoomMapper;
import projeto.arenaFuria.repository.MemberRepository;
import projeto.arenaFuria.repository.RoomRepository;
import projeto.arenaFuria.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public RoomRegisterResponseDTO createRoom(RoomRegisterDTO dto, String userId) throws Exception {
        User user = userRepository.findUserById(userId);
        
        if(user.getRole() != UserRole.ADMIN) {
            throw new RuntimeException("Apenas administradores podem criar salas");
        }

        var room = roomMapper.registerToEntity(dto);
        room = roomRepository.save(room);
        
        // Cria e adiciona o membro OWNER
        Member ownerMember = new Member();
        ownerMember.setUserId(user);
        ownerMember.setRoomId(room);
        ownerMember.setRoomRole(RoomRoles.OWNER);
        ownerMember.setJoinedAt(LocalDateTime.now());
        memberRepository.save(ownerMember);
        
        room.getMembers().add(ownerMember);
        return roomMapper.entityToResponse(room);
    }

    public ResultResponseDTO joinRoom(String roomName, String userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        Room room = roomRepository.findByName(roomName)
                .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada com o nome: " + roomName));

        // Verifica se o usuário já é membro
        if (memberRepository.findByUserId_IdAndRoomId_Id(userId, room.getId()) != null) {
            throw new IllegalStateException("Usuário já é membro desta sala");
        }

        // Define o papel do usuário na sala (OWNER para admin, MEMBER para usuário comum)
        RoomRoles role = user.getRole() == UserRole.ADMIN ? RoomRoles.OWNER : RoomRoles.MEMBER;

        // Cria e adiciona o novo membro
        Member member = new Member();
        member.setUserId(user);
        member.setRoomId(room);
        member.setRoomRole(role);
        member.setJoinedAt(LocalDateTime.now());
        memberRepository.save(member);

        room.getMembers().add(member);
        roomRepository.save(room);
        
        return new ResultResponseDTO(true, "Usuário entrou na sala com sucesso");
    }

    private void validateRoomExists(String roomId) {
        roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada com o ID: " + roomId));
    }

    private void validateUserExists(String userId) {
        if (userService.getUserById(userId) == null) {
            throw new EntityNotFoundException("Usuário não encontrado com o ID: " + userId);
        }
    }

    private void validateUserNotAlreadyMember(MemberRegisterDTO memberDto) {
        if (memberRepository.findByUserId_IdAndRoomId_Id(memberDto.userId(), memberDto.roomId()) != null) {
            throw new IllegalStateException("Usuário já é membro desta sala");
        }
    }

    public ResultResponseDTO deleteRoom(String roomId, String userId) {
        var user = userRepository.findUserById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }

        if (user.getRole() != UserRole.ADMIN) {
            throw new RuntimeException("Only admins can delete rooms");
        }

        var room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            throw new EntityNotFoundException("Room not found");
        }

        // Deleta os membros relacionados à sala (para evitar erro de integridade referencial)
        memberRepository.deleteByRoomId_Id(roomId);

        // Deleta a sala
        roomRepository.deleteById(roomId);

        return new ResultResponseDTO(true, "Room deleted successfully");
    }

    public Room getRoomByName(String roomName) {
        return roomRepository.findByName(roomName)
                .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada com o nome: " + roomName));
    }

}
