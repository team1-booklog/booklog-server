package goorm.unit.booklog.domain.book.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goorm.unit.booklog.domain.book.application.BookService;
import goorm.unit.booklog.domain.book.presentation.response.BookPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@Tag(name = "Book", description = "도서 관련 api / 담당자 : 이한음")
public class BookController {
	private final BookService bookService;

	@Operation(summary = "도서 검색", description = "네이버 도서 검색 Open API에서 키워드 기반으로 도서를 검색 합니다.")
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "도서 검색 성공",
			content = @Content(schema = @Schema(implementation = BookPageResponse.class))

		)
	})
	@GetMapping("/search")
	public ResponseEntity<BookPageResponse> searchBooks(
		@Parameter(description = "페이지 인덱스", example = "1", required = true) @RequestParam(value = "page", defaultValue = "1") int page,
		@Parameter(description = "응답 개수", example = "10", required = true) @RequestParam(value = "size", defaultValue = "10") int size,
		@Parameter(description = "키워드", example = "스프링", required = true) @RequestParam(value = "keyword") String keyword
	) {
		BookPageResponse response = bookService.searchBooks(page, size, keyword);
		return ResponseEntity.ok(response);
	}
}
