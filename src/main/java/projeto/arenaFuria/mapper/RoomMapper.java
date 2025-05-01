package projeto.arenaFuria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import projeto.arenaFuria.dto.register.RoomRegisterDTO;
import projeto.arenaFuria.dto.register.RoomRegisterResponseDTO;
import projeto.arenaFuria.dto.register.RoomWithMembersDTO;
import projeto.arenaFuria.entity.model.Room;

@Mapper(componentModel = "spring", uses = {MemberMapper.class})
public interface RoomMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "members", ignore = true)
    Room registerToEntity(RoomRegisterDTO dto);

    RoomRegisterResponseDTO entityToResponse(Room room);

    @Mapping(target = "members", source = "members")
    RoomWithMembersDTO entityToRoomWithMembers(Room room);
}
