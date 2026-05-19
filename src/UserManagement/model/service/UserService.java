package UserManagement.model.service;

import UserManagement.model.dto.CreateUserDto;
import UserManagement.model.dto.UpdateRequestDto;
import UserManagement.model.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(CreateUserDto createUserDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto updateRequestDto);
    int deleteUserByUuid(String uuid);
    List<UserResponseDto> searchUserByName(String name);
}
