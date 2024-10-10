package goorm.unit.booklog.domain.review.application;

import goorm.unit.booklog.domain.book.application.BookService;
import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.book.infrastructure.BookRepositoryImpl;
import goorm.unit.booklog.domain.file.domain.File;
import goorm.unit.booklog.domain.review.domain.Review;
import goorm.unit.booklog.domain.review.domain.ReviewRepository;
import goorm.unit.booklog.domain.review.presentation.request.ReviewCreateRequest;
import goorm.unit.booklog.domain.review.presentation.response.ReviewPersistResponse;
import goorm.unit.booklog.domain.review.presentation.response.ReviewResponse;
import goorm.unit.booklog.domain.user.application.UserService;
import goorm.unit.booklog.domain.user.domain.User;
import goorm.unit.booklog.domain.review.presentation.exception.ReviewNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepositoryImpl bookRepository;
    private final UserService userService;
    private final BookService bookService;

    @Transactional
    public ReviewPersistResponse createReview(ReviewCreateRequest request) {
        User user = userService.me();
        File file=File.of(
                request.title(),
                request.img()
        );
        Book book = bookService.getBook(request.book_id());
        Review review = Review.create(
                request.title(),
                request.content(),
                file,
                user,
                book
        );
        Long id = reviewRepository.save(review).getId();
        return ReviewPersistResponse.of(id);
    }

    @Transactional
    public ReviewResponse getReview(Long id) {
        Review review= reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
        return ReviewResponse.of(review);
    }
}
