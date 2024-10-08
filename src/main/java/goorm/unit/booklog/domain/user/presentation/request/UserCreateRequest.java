package goorm.unit.booklog.domain.user.presentation.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserCreateRequest(
        @Schema(description = "아이디", example = "id1234", requiredMode = REQUIRED)
        @NotNull
        @Size(min = 4, max = 16, message = "아이디는 4자에서 16자 사이여야 합니다.")
        @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 영문 소문자와 숫자로만 구성되어야 합니다.")
        String id,

        @Schema(description = "이름",requiredMode = REQUIRED)
        @NotNull
        String name,

        @Schema(description = "비밀번호", example = "password1234", requiredMode = REQUIRED)
        @NotNull
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "비밀번호는 최소 8자리 이상, 영문자와 숫자를 포함해야 합니다.")
        String password
) {
}