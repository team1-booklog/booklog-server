package goorm.unit.booklog.domain.login.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AccessTokenResponse(
        @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyfQ", requiredMode = REQUIRED)
        String accessToken
) {
    public static AccessTokenResponse of(String accessToken) {
        return AccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
