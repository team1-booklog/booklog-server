package goorm.unit.booklog.domain.book.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import goorm.unit.booklog.common.response.PageableResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record BookPageResponse<T>(
	@Schema(
		description = "도서 목록",
		example = "[{\"id\": 1, \"title\": \"스프링 부트와 AWS로 혼자 구현하는 웹 서비스\", \"author\": \"이한음\", \"description\": \"스프링 부트와 AWS로 혼자 구현하는 웹 서비스\", \"file\": {\"id\": 1, \"logicalName\": \"example.jpg\", \"physicalPath\": \"https://example-bucket.ncp.com/files/example.jpg\"}}]",
		requiredMode = REQUIRED
	)
	List<BookResponse> contents,

	@Schema(description = "페이징 정보", requiredMode = REQUIRED)
	PageableResponse<T> pageable
) {
	public static <T> BookPageResponse<T> of(List<BookResponse> books, PageableResponse<T> pageableResponse) {
		return BookPageResponse.<T>builder()
				.contents(books)
				.pageable(pageableResponse)
				.build();
	}
}
