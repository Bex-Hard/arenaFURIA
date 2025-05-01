package projeto.arenaFuria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import projeto.arenaFuria.dto.register.MemberRegisterDTO;
import projeto.arenaFuria.dto.register.MemberRegisterResponseDTO;
import projeto.arenaFuria.entity.model.Member;
import projeto.arenaFuria.entity.model.Room;
import projeto.arenaFuria.entity.model.User;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberMapper {

    // DTO de registro para entidade
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", source = "dto.userId", qualifiedByName = "stringToUser")
    @Mapping(target = "room",   source = "dto.roomId", qualifiedByName = "stringToRoom")
    @Mapping(target = "joinedAt", expression = "java(java.time.LocalDateTime.now())")
    Member registerToEntity(MemberRegisterDTO dto);

    // Entidade para DTO de resposta
    @Mapping(target = "id",       source = "entity.id")
    @Mapping(target = "roomId",   source = "entity.room.id")
    @Mapping(target = "email",    source = "entity.userId.email")
    @Mapping(target = "roomRole", source = "entity.roomRole")
    @Mapping(target = "joinedAt", source = "entity.joinedAt")
    MemberRegisterResponseDTO entityToResponse(Member entity);

    // Converte apenas o ID (String) para a entidade User vazia
    @Named("stringToUser")
    default User stringToUser(String userId) {
        if (userId == null) {
            return null;
        }
        User u = new User();
        u.setId(userId);
        return u;
    }

    // Converte apenas o ID (String) para a entidade Room vazia
    @Named("stringToRoom")
    default Room stringToRoom(String roomId) {
        if (roomId == null) {
            return null;
        }
        Room r = new Room();
        r.setId(roomId);
        return r;
    }
}
