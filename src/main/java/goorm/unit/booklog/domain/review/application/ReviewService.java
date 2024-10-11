package goorm.unit.booklog.domain.review.application;

import static goorm.unit.booklog.domain.review.domain.ReviewStatus.ACTIVE;
import static goorm.unit.booklog.domain.review.domain.ReviewStatus.INACTIVE;

import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.book.domain.BookRepository;
import goorm.unit.booklog.domain.book.presentation.exception.BookNotFoundException;
import goorm.unit.booklog.domain.review.presentation.response.ReviewListResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import goorm.unit.booklog.domain.book.application.BookService;
import goorm.unit.booklog.domain.file.application.FileService;
import goorm.unit.booklog.domain.file.domain.File;
import goorm.unit.booklog.domain.review.domain.Review;
import goorm.unit.booklog.domain.review.domain.ReviewRepository;
import goorm.unit.booklog.domain.review.presentation.exception.ReviewNotFoundException;
import goorm.unit.booklog.domain.review.presentation.request.ReviewCreateRequest;
import goorm.unit.booklog.domain.review.presentation.request.ReviewUpdateRequest;
import goorm.unit.booklog.domain.review.presentation.response.ReviewPersistResponse;
import goorm.unit.booklog.domain.review.presentation.response.ReviewResponse;
import goorm.unit.booklog.domain.user.application.UserService;
import goorm.unit.booklog.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final BookService bookService;
    private final FileService fileService;
    private final BookRepository bookRepository;

    @Transactional
    public ReviewPersistResponse createReview(MultipartFile file, ReviewCreateRequest request) {
        User user = userService.me();
        File uploadedFile = null;
        if (file != null) {
            uploadedFile = fileService.uploadAndSaveFile(file);
        }
        Review review = Review.create(
                request.title(),
                request.content(),
                uploadedFile,
                user,
                bookService.getBookById(request.bookId())
        );
        Long id = reviewRepository.save(review).getId();
        return ReviewPersistResponse.of(id);
    }

    @Transactional
    public ReviewResponse getReview(Long id) {
        Review review= reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
        return ReviewResponse.of(review);
    }

    @Transactional
    public void updateReview(Long id, MultipartFile file, ReviewUpdateRequest request) {
        Review review = getReviewById(id);
        review.updateTitle(request.title());
        review.updateContent(request.content());

        File uploadedFile = null;
        if (file != null) {
            uploadedFile = fileService.uploadAndSaveFile(file);
        }
        review.updateFile(uploadedFile);
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = getReviewById(id);
        review.updateStatus(INACTIVE);
    }

    private Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
    }

    public ReviewListResponse getReviewListByBook(Long bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        List<Review> reviews=reviewRepository.findAllByBook(book);
        List<ReviewResponse> reviewResponses=new ArrayList<>();
        for(Review review:reviews) {
            if(review.getStatus().equals(ACTIVE)) {
                ReviewResponse reviewResponse=ReviewResponse.of(review);
                reviewResponses.add(reviewResponse);
            }
        }
        return new ReviewListResponse(reviewResponses);
    }
}
