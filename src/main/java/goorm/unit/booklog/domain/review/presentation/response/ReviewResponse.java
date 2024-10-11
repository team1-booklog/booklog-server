package goorm.unit.booklog.domain.review.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import goorm.unit.booklog.domain.book.presentation.response.BookResponse;
import goorm.unit.booklog.domain.file.presentation.response.FileResponse;
import goorm.unit.booklog.domain.review.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ReviewResponse(
        @Schema(description = "독후감 ID", example = "1", requiredMode = REQUIRED)
        Long id,

        @Schema(description = "독후감 제목", example = "해리포터를 읽고", requiredMode = REQUIRED)
        String title,

        @Schema(description = "독후감 본문", example = "이번 기회에 해리포터라는 책을 읽게 되었다.", requiredMode = NOT_REQUIRED)
        String content,

        @Schema(description = "작성자 이름", example = "홍길동", requiredMode = REQUIRED)
        String name,

        @Schema(
            description = "독후감 사진",
            example = "{\"id\": 1, \"logicalName\": \"example.jpg\", \"physicalPath\": \"https://example-bucket.ncp.com/files/example.jpg\"}",
            requiredMode = NOT_REQUIRED)
        FileResponse file,

        @Schema(
            description = "도서 목록",
            example = "{\"id\": 1, \"title\": \"스프링 부트와 AWS로 혼자 구현하는 웹 서비스\", \"author\": \"이한음\", \"description\": \"스프링 부트와 AWS로 혼자 구현하는 웹 서비스\", \"file\": {\"id\": 1, \"logicalName\": \"example.jpg\", \"physicalPath\": \"https://example-bucket.ncp.com/files/example.jpg\"}}",
            requiredMode = REQUIRED
        )
        BookResponse book,

        @Schema(description = "작성 일자", example = "2024-10-10", requiredMode = REQUIRED)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        String createdAt,

        @Schema(description = "수정 일자", example = "2024-10-10", requiredMode = REQUIRED)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        String updatedAt
) {
    public static ReviewResponse of(Review review) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ReviewResponse.builder()
            .id(review.getId())
            .title(review.getTitle())
            .name(review.getUser().getName())
            .content(review.getContent())
            .file(review.getFile() != null ? FileResponse.from(review.getFile()) : null)
            .book(BookResponse.from(review.getBook()))
            .createdAt(review.getCreatedAt().format(formatter))
            .updatedAt(review.getUpdatedAt().format(formatter))
            .build();
    }
}
