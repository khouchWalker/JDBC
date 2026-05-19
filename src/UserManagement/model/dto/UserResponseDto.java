package UserManagement.model.dto;

public record UserResponseDto(
        String uuid,
        String name,
        String email,
        String profile,
        Integer id
) {

}
