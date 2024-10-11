package goorm.unit.booklog.domain.review.application;

import static goorm.unit.booklog.domain.review.domain.ReviewStatus.INACTIVE;

import goorm.unit.booklog.domain.book.domain.Book;
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

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final BookService bookService;
    private final FileService fileService;

    @Transactional
    public ReviewPersistResponse createReview(MultipartFile file, ReviewCreateRequest request) {
        User user = userService.me();
        Book book= bookService.getBookById(request.bookId());

        File uploadedFile = null;
        if (file != null) {
            uploadedFile = fileService.uploadAndSaveFile(file);
        }

        Review review = Review.create(
                request.title(),
                request.content(),
                uploadedFile,
                user,
                book
        );

        Long id = reviewRepository.save(review).getId();

        user.updateReview(review);
        user.updateBook(book);
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
}
