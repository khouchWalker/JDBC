package UserManagement.utils;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record APIResponse<T>(
        int status,
        String message,
        LocalDate timeStamp,
        T data
) { }
