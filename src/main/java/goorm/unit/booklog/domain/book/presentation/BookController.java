package goorm.unit.booklog.domain.book.presentation;

import goorm.unit.booklog.domain.book.presentation.response.BookListResponse;
import goorm.unit.booklog.domain.book.presentation.response.BookSummaryResponse;
import goorm.unit.booklog.domain.book.presentation.response.UserBookListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Operation(summary = "기본 도서 목록 조회", description = "랜덤으로 요청한 개수 만큼의 도서 목록을 반환합니다.")
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "기본 도서 목록 조회 성공",
			content = @Content(schema = @Schema(implementation = BookListResponse.class))
		)
	})
	@GetMapping
	public ResponseEntity<BookListResponse> getDefaultBookList(
		@Parameter(description = "응답 개수", example = "10", required = true) @RequestParam(value = "size", defaultValue = "10") int size
	) {
		BookListResponse response = bookService.getDefaultBookList(size);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "isbn에 해당하는 도서 ID 반환", description = "isbn에 해당하는 도서 ID를 반환합니다.")
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "isbn에 해당하는 도서 ID 반환 성공",
			content = @Content(schema = @Schema(implementation = BookSummaryResponse.class))
		)
	})
	@GetMapping("/{isbn}")
	public ResponseEntity<BookSummaryResponse> getBookIdByIsbn(
		@Parameter(description = "isbn", example = "9788953583979", required = true) @PathVariable(value = "isbn") String isbn
	) {
		BookSummaryResponse response = bookService.getBookIdByIsbn(isbn);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "유저 활동 내역 조회", description = " 작성한 독후감의 수와 읽은 책의 수, 표지, 제목을 반환합니다.")
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "유저 활동 내역 조회 성공",
			content = @Content(schema = @Schema(implementation = UserBookListResponse.class))
		)
	})
	@GetMapping("/me")
	public ResponseEntity<UserBookListResponse> getMyBookList() {
		UserBookListResponse response = bookService.getMyBookList();
		return ResponseEntity.ok(response);
	}
}
