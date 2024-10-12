package goorm.unit.booklog.domain.book.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import goorm.unit.booklog.domain.book.domain.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record BookSummaryResponse(
	@Schema(description = "도서 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "도서 제목", example = "스프링 부트와 AWS로 혼자 구현하는 웹 서비스", requiredMode = REQUIRED)
	String title
) {
	public static BookSummaryResponse from (Book book) {
		return BookSummaryResponse.builder()
			.id(book.getId())
			.title(book.getTitle())
			.build();
	}
}
