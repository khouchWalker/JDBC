package UserManagement.model.dto;

import lombok.Builder;

@Builder
public record CreateUserDto(
        Integer id,
        String uuid,
        String name,
        String email,
        String password,
        String profile
) { }
