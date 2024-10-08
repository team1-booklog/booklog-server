package goorm.unit.booklog.domain.login.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @Schema(description = "아이디", example = "id1234", requiredMode = REQUIRED)
        @NotNull
        String id,

        @Schema(description = "비밀번호", example = "password1234", requiredMode = REQUIRED)
        @NotNull
        String password
) {
}
