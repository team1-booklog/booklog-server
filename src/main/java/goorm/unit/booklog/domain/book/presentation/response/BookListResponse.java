package goorm.unit.booklog.domain.book.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import goorm.unit.booklog.common.response.PageableResponse;
import goorm.unit.booklog.domain.book.domain.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record BookListResponse(
	@Schema(
		description = "도서 목록",
		example = "[{\"id\": 1, \"title\": \"스프링 부트와 AWS로 혼자 구현하는 웹 서비스\", \"author\": \"이동욱\", \"description\": \"이 책은 스프링부트 입문자용 책입니다.\", \"file\": {\"id\": 1, \"logicalName\": \"example.jpg\", \"physicalPath\": \"https://example-bucket.ncp.com/files/example.jpg\"}}]",
		requiredMode = REQUIRED
	)
	List<BookResponse> contents
) {
	public static BookListResponse of(List<Book> books) {
		return BookListResponse.builder()
			.contents(books.stream()
				.map(BookResponse::from)
				.toList())
			.build();
	}
}
