package UserManagement.mapper;

import UserManagement.model.User;
import UserManagement.model.dto.CreateUserDto;
import UserManagement.model.dto.UserResponseDto;

import java.util.Random;
import java.util.UUID;

public class UserMapper {
    public User fromCreateUserDtoToUser(CreateUserDto createUserDto){
        return new User(new Random().nextInt(999999),
                UUID.randomUUID().toString(),
                createUserDto.name(),
                createUserDto.email(),
                createUserDto.password(),
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmmy9zN-EAn_r0jOm84edG1XOYmYTtIMnONj-eGqHKiZATXJxGK4j0fgD9LNbW5FnDTEVv-C8ch2Stm6BK16yhGNmVgewWuDNDgMNv-mD2mw&s=10"
        );
    }
    public UserResponseDto fromUserToUserResponseDto(User user){
        return new UserResponseDto(UUID.randomUUID().toString(),
                user.getName(),user.getEmail(),user.getProfile(),user.getId());
    }
}
