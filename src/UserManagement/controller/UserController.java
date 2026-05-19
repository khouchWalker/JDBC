package UserManagement.controller;

import UserManagement.model.dto.CreateUserDto;
import UserManagement.model.dto.UpdateRequestDto;
import UserManagement.model.dto.UserResponseDto;
import UserManagement.model.service.UserService;
import UserManagement.model.service.UserServiceImpl;
import UserManagement.utils.APIResponse;

import java.time.LocalDate;
import java.util.List;

public class UserController {
    private final UserService userService = new UserServiceImpl();

    public APIResponse<UserResponseDto> createUser(CreateUserDto createUserDto) {
        return APIResponse.<UserResponseDto>builder()
                .status(201)
                .message("Created user successfully 😎")
                .data(userService.createUser(createUserDto))
                .timeStamp(LocalDate.now())
                .build();
    }
    public APIResponse<List<UserResponseDto>> getAllUser(){
        return  new APIResponse<>(
                200,
                "Get all users successfully",
                LocalDate.now(),
                userService.getAllUsers()
        );
    }
    public APIResponse<UserResponseDto> getUserById(String uuid) {
        UserResponseDto userResponseDto = userService.getUserUuid(uuid);
        return new APIResponse<>(
                userResponseDto != null ? 202 : 404,
                userResponseDto != null ? "User Found!" : "User Not Found!",
                LocalDate.now(),
                userResponseDto
        );
    }

    public APIResponse<UserResponseDto> updateUserByUuid(int id, UpdateRequestDto updateRequestDto) {
        UserResponseDto userResponseDto = userService.updateUserByid(id, updateRequestDto);
        return new APIResponse<>(
                userResponseDto != null ? 200 : 404,
                userResponseDto != null ? "User updated successfully 😎" : "User Not Found!",
                LocalDate.now(),
                userResponseDto
        );
    }

    public APIResponse<Integer> deleteUserByid(int id) {
        int result = userService.deleteUserByid(id);
        return new APIResponse<>(
                result == 1 ? 200 : 404,
                result == 1 ? "User deleted successfully" : "User Not Found!",
                LocalDate.now(),
                result
        );
    }

    public APIResponse<List<UserResponseDto>> searchUserByName(String name) {
        List<UserResponseDto> users = userService.searchUserByName(name);
        return new APIResponse<>(
                200,
                "Users found",
                LocalDate.now(),
                users
        );
    }

}
