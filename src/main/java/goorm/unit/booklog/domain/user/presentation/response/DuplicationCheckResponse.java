package goorm.unit.booklog.domain.user.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record DuplicationCheckResponse(
        @Schema(description = "유저 id", example = "1",requiredMode = REQUIRED)
        String id) {
    public static DuplicationCheckResponse of(String id) {
        return new DuplicationCheckResponse(id);
    }
}

