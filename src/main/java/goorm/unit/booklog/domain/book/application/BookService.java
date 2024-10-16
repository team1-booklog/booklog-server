package goorm.unit.booklog.domain.book.application;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import goorm.unit.booklog.common.response.PageableResponse;
import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.book.domain.BookRepository;
import goorm.unit.booklog.domain.book.presentation.exception.BookNotFoundException;
import goorm.unit.booklog.domain.book.presentation.response.BookListResponse;
import goorm.unit.booklog.domain.book.presentation.response.BookPageResponse;
import goorm.unit.booklog.domain.book.presentation.response.BookResponse;
import goorm.unit.booklog.domain.book.presentation.response.BookSummaryResponse;
import goorm.unit.booklog.domain.book.presentation.response.UserBookListResponse;
import goorm.unit.booklog.domain.file.domain.File;
import goorm.unit.booklog.domain.review.domain.Review;
import goorm.unit.booklog.domain.user.application.UserService;
import goorm.unit.booklog.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;
	private final UserService userService;

	@Value("${naver.api.clientId}")
	private String clientId;

	@Value("${naver.api.clientSecret}")
	private String clientSecret;

	public BookPageResponse searchBooks(int page, int size, String keyword) {
		URI uri = UriComponentsBuilder
			.fromUriString("https://openapi.naver.com")
			.path("/v1/search/book.json")
			.queryParam("query", keyword)
			.queryParam("display", size)
			.queryParam("start", page)
			.queryParam("sort", "sim")
			.encode()
			.build()
			.toUri();

		RequestEntity<Void> req = RequestEntity
			.get(uri)
			.header("X-Naver-Client-Id", clientId)
			.header("X-Naver-Client-Secret", clientSecret)
			.build();

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.exchange(req, String.class).getBody();

		JSONObject jsonResponse = new JSONObject(response);
		JSONArray items = jsonResponse.getJSONArray("items");

		List<BookResponse> bookResponses = new ArrayList<>();
		for (int i = 0; i < items.length(); i++) {
			JSONObject item = items.getJSONObject(i);

			Book book;
			String title = item.getString("title");
			String author = item.getString("author");
			String description=item.getString("description");
			String image = item.getString("image");
			String isbn = item.getString("isbn");

			Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(title, author);
			if (existingBook.isEmpty()) {
				File file = File.of(title, image);
				book = Book.create(title, author, description, isbn, file);
				bookRepository.save(book);
			}
			else{
				book= existingBook.get();
			}

			BookResponse bookResponse = BookResponse.from(book);
			bookResponses.add(bookResponse);
		}
		int total = jsonResponse.getInt("total");
		return BookPageResponse.of(bookResponses, PageableResponse.of(PageRequest.of(page, size), (long)total));
	}

	public BookListResponse getDefaultBookList(int size) {
		List<Book> books = bookRepository.findAll();
		Collections.shuffle(books);
		List<Book> randomBooks = books.stream()
			.limit(size)
			.collect(Collectors.toList());
		return BookListResponse.of(randomBooks);
	}

	public BookSummaryResponse getBookIdByIsbn(String isbn) {
		Book book = bookRepository.findByIsbn(isbn).orElseThrow(BookNotFoundException::new);
		return BookSummaryResponse.from(book);
	}

	public Book getBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Transactional
	public UserBookListResponse getMyBookList() {
		User user=userService.me();
		List<Book> books=user.getBooks();
		List<Review> reviews=user.getReviews();

		List<BookResponse> bookResponses = new ArrayList<>();

		for (Book book : books) {
			BookResponse bookResponse = BookResponse.from(book);
			bookResponses.add(bookResponse);
		}
		return UserBookListResponse.of(user.getId(), user.getName(), books.size(), reviews.size(),bookResponses);
	}

}
