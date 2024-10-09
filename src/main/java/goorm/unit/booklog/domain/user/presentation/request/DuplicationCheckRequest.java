package goorm.unit.booklog.domain.user.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record DuplicationCheckRequest (
    @Schema(description = "아이디", example = "id1234", requiredMode = REQUIRED)
    @NotNull
    @Size(min = 4, max = 16, message = "아이디는 4자에서 16자 사이여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 영문 소문자와 숫자로만 구성되어야 합니다.")
    String id
){}
