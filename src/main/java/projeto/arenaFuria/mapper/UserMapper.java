package projeto.arenaFuria.mapper;

import projeto.arenaFuria.dto.register.UserRegisterResponseDTO;
import projeto.arenaFuria.entity.model.User;

public interface UserMapper {
    static UserRegisterResponseDTO createUserResgisterDTO(User user){
        return UserRegisterResponseDTO.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
