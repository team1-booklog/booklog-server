package goorm.unit.booklog.domain.review.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewCreateRequest (
    @Schema(description = "제목", example = "해리포터를 읽고", requiredMode = REQUIRED)
    @NotNull @Size(max = 50, message = "독후감 제목은 50자 이내여야합니다.")
    String title,

    @Schema(description = "본문", example="해리포터라는 책을 읽었다.", requiredMode = REQUIRED)
    @NotNull
    String content,

    @Schema(description = "도서 ID", example = "1", requiredMode = REQUIRED)
    Long bookId
){

}
