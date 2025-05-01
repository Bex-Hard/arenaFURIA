package projeto.arenaFuria.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.arenaFuria.dto.register.*;
import projeto.arenaFuria.dto.register.RoomWithMembersDTO;
import projeto.arenaFuria.entity.enums.RoomRoles;
import projeto.arenaFuria.entity.model.Room;
import projeto.arenaFuria.mapper.RoomMapper;
import projeto.arenaFuria.services.RoomService;
import projeto.arenaFuria.services.UserService;
import projeto.arenaFuria.services.MemberService;


@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RoomController {

    private final RoomService roomService;
    private final UserService userService;
    private final MemberService memberService;
    private final RoomMapper roomMapper;

    @PostMapping("/create")
    public ResponseEntity<RoomRegisterResponseDTO> create(@RequestBody RoomRegisterDTO dto, HttpServletRequest request) throws Exception {
        String userId = userService.getRequestUserId(request);
        var room = roomService.createRoom(dto, userId);
        return ResponseEntity.ok(room);
    }

    @PostMapping("/join")
    public ResponseEntity<ResultResponseDTO> joinRoom(@RequestBody JoinRoomDTO joinRoomDTO, HttpServletRequest request) {
        String userId = userService.getRequestUserId(request);
        var result = roomService.joinRoom(joinRoomDTO.roomName(), userId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<ResultResponseDTO> deleteRoom(@PathVariable String roomId, HttpServletRequest request) {
        String userId = userService.getRequestUserId(request);
        var result = roomService.deleteRoom(roomId, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{roomName}")
    public ResponseEntity<RoomWithMembersDTO> getRoomDetails(@PathVariable String roomName) {
        Room room = roomService.getRoomByName(roomName);
        return ResponseEntity.ok(roomMapper.entityToRoomWithMembers(room));
    }

}
