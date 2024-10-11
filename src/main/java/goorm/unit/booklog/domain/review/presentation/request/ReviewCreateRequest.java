package goorm.unit.booklog.domain.review.presentation.request;

import goorm.unit.booklog.domain.book.presentation.response.BookResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record ReviewCreateRequest (
        @Schema(description = "제목", example = "해리포터를 읽고", requiredMode = REQUIRED)
        @NotNull
        @Size(max = 50, message = "독후감 제목은 50자 이내여야합니다.")
        String title,

        @Schema(description = "본문", example="해리포터라는 책을 읽었다.", requiredMode = REQUIRED)
        @NotNull
        String content,

        @Schema(description = "썸네일 이미지", example = "", requiredMode = REQUIRED)
        @NotNull
        String img,

        @Schema(description = "책 정보",
                example = "{"
                        + "\"id\": 1,"
                        + "\"title\": \"어린 왕자\","
                        + "\"author\": \"생텍쥐페리\","
                        + "\"description\": \"책 어린왕자는 완전하지 못한 너와 내가 서로 어우러져 살아가는 방법에 대한 메시지를 전한다\","
                        + "\"file\": {"
                        + "    \"id\": 1,"
                        + "    \"logicalName\": \"어린 왕자 대표 이미지\","
                        + "    \"physicalPath\": \"https://search.shopping.naver.com/book/catalog/48953406622\""
                        + "}"
                        + "}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        BookResponse bookResponse

){

}
