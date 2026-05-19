package UserManagement.model.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String uuid,
        String name,
        String email,
        String profile,
        Integer id
) {

}
