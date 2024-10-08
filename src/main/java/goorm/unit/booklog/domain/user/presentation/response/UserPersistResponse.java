package goorm.unit.booklog.domain.user.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserPersistResponse(
        @Schema(description = "유저 ID", example = "1", requiredMode = REQUIRED)
        String id
) {
    public static UserPersistResponse of(String id) {
        return new UserPersistResponse(id);
    }
}
