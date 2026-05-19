package UserManagement.model.service;

import UserManagement.mapper.UserMapper;
import UserManagement.model.User;
import UserManagement.model.UserDao;
import UserManagement.model.dto.CreateUserDto;
import UserManagement.model.dto.UpdateRequestDto;
import UserManagement.model.dto.UserResponseDto;

import java.util.List;

public class UserServiceImpl implements  UserService {
    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper = new UserMapper();

    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {
//        create user
        User user = userMapper.fromCreateUserDtoToUser(createUserDto);
        userDao.save(user); // save user

//        map from User to UserResponseDto
        UserResponseDto userResponseDto
                = userMapper.fromUserToUserResponseDto(user);
        System.out.println(user.getUuid());
        return userResponseDto;
    }
    @Override
    public List<UserResponseDto> getAllUsers() {
//        using map to convert object of User to UserResponseDto
//        List<User> users = userDao.findAll();
//        List<UserResponseDto> userResponseDtos = new ArrayList<>();
//        for (User user: users){
//            UserResponseDto userResponseDto = new UserResponseDto(
//                    user.getUuid(),user.getName(),user.getEmail(),user.getProfile()
//            );
//            userResponseDtos.add(userResponseDto);
//        }
//        return userResponseDtos;

        return userDao.findAll().stream()
                .map(userMapper::fromUserToUserResponseDto).toList();
    }

    @Override
    public UserResponseDto getUserUuid(String uuid) {
        return userDao.findAll()
                .stream().filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .map(userMapper::fromUserToUserResponseDto)
                .orElse(null);
    }

    @Override
    public UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto updateRequestDto) {
        User user = userDao.findAll()
                .stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
        if (user != null) {
            user.setName(updateRequestDto.name());
            user.setEmail(updateRequestDto.email());
            user.setPassword(updateRequestDto.password());
            user.setProfile(updateRequestDto.profile());
            userDao.update(user);
            return userMapper.fromUserToUserResponseDto(user);
        }
        return null;
    }

    @Override
    public int deleteUserByUuid(String uuid) {
        User user = userDao.findAll()
                .stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
        if (user != null) {
            userDao.remove(user);
            return 1;
        }
        return 0;
    }

    @Override
    public List<UserResponseDto> searchUserByName(String name) {
        return userDao.findAll().stream()
                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                .map(userMapper::fromUserToUserResponseDto)
                .toList();
    }
}
