package goorm.unit.booklog.domain.review.service;

import goorm.unit.booklog.domain.review.model.Review;
import goorm.unit.booklog.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 독후감 생성
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    // 모든 독후감 조회
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 특정 독후감 조회
    public Review getReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    // 독후감 수정
    public Review updateReview(Long id, Review reviewDetails) {
        Review review = getReview(id);
        review.setTitle(reviewDetails.getTitle());
        review.setContent(reviewDetails.getContent());
        review.setAuthor(reviewDetails.getAuthor());
        return reviewRepository.save(review);
    }

    // 독후감 삭제
    public void deleteReview(Long id) {
        Review review = getReview(id);
        reviewRepository.delete(review);
    }
}

