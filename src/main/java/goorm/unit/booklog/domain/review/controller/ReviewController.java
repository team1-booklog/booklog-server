package goorm.unit.booklog.domain.review.controller;

import goorm.unit.booklog.domain.review.model.Review;
import goorm.unit.booklog.domain.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{review_id}")
    public ResponseEntity<Review> getReview(@PathVariable Long review_id) {
        Review review = reviewService.getReviewById(review_id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/{review_id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long review_id, @RequestBody Review reviewDetails) {
        Review updatedReview = reviewService.updateReview(review_id, reviewDetails);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{review_id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long review_id) {
        reviewService.deleteReview(review_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
