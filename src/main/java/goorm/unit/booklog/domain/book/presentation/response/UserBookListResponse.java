package goorm.unit.booklog.domain.book.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record UserBookListResponse(
        @Schema(description = "유저 아이디", example = "id1234", requiredMode = REQUIRED)
        String userId,

        @Schema(description = "유저 이름", example = "홍길동", requiredMode = REQUIRED)
        String userName,

        @Schema(description = "읽은 책 수", example = "1", requiredMode = REQUIRED)
        Integer bookCount,

        @Schema(description = "작성한 독후감 수", example = "1", requiredMode = REQUIRED)
        Integer reviewCount,

        @Schema(
                description = "읽은 책 목록",
                example = "[{\"id\": 1, \"title\": \"스프링 부트와 AWS로 혼자 구현하는 웹 서비스\", \"author\": \"이한음\", \"description\": \"스프링 부트와 AWS로 혼자 구현하는 웹 서비스\", \"file\": {\"id\": 1, \"logicalName\": \"example.jpg\", \"physicalPath\": \"https://example-bucket.ncp.com/files/example.jpg\"}}]",
                requiredMode = REQUIRED
        )
        List<BookResponse> bookResponses
) {
        public static UserBookListResponse of(String userId, String userName, Integer bookCount, Integer reviewCount, List<BookResponse> bookResponses) {
                return UserBookListResponse.builder()
                        .userId(userId)
                        .userName(userName)
                        .bookCount(bookCount)
                        .reviewCount(reviewCount)
                        .bookResponses(bookResponses)
                        .build();
        }
}
