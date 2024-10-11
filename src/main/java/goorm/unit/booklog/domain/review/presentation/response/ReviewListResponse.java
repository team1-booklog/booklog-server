package goorm.unit.booklog.domain.review.presentation.response;

import goorm.unit.booklog.domain.book.presentation.response.BookResponse;
import goorm.unit.booklog.domain.file.presentation.response.FileResponse;
import goorm.unit.booklog.domain.review.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record ReviewListResponse (
    @Schema(description = "리뷰 리스트",
            example ="[{\"id\": 5," +
                    "\"title\": \"스프링 부트에 대한 책을 읽고\"," +
                    "\"content\": \"유익한 책이었다.\"," +
                    "\"name\": \"홍길동\"," +
                    "\"userId\": \"id1234\"," +
                    "\"file\":{\"id\": 1, \"logicalName\": \"독후감 썸네일.jpg\", \"physicalPath\": \"https://example-domain.com/files/example.jpg\"}, " +
                    "\"book\":{\"id\": 1, " +
                    "\"title\": \"스프링 부트와 AWS로 혼자 구현하는 웹 서비스\", " +
                    "\"author\": \"이동욱\", " +
                    "\"description\": \"해당 책은 스프링 부트 입문자용 책입니다.\", " +
                    "\"file\": {\"id\": 1, \"logicalName\": \"책 표지.jpg\", \"physicalPath\": \"https://example-bucket.ncp.com/files/example.jpg\"}}," +
                    "\"createAt\":\"2024-10-11\"," +
                    "\"updatedAt\":\"2024-10-12\"}]", requiredMode = REQUIRED)
    List<ReviewResponse> reviews
){
    public static ReviewListResponse of(List<ReviewResponse> reviews) {
        return ReviewListResponse.builder()
                .reviews(reviews)
                .build();
    }
}
