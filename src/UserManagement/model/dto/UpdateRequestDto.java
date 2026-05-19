package UserManagement.model.dto;

public record UpdateRequestDto(
        String name,
        String email,
        String password,
        String profile
) { }
