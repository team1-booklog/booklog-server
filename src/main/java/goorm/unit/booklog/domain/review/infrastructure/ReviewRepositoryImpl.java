package goorm.unit.booklog.domain.review.infrastructure;

import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.book.infrastructure.JpaBookRepository;
import goorm.unit.booklog.domain.review.domain.Review;
import goorm.unit.booklog.domain.review.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final JpaReviewRepository jpaReviewRepository;

    @Override
    public Review save(Review review) {
        return jpaReviewRepository.save(review);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return jpaReviewRepository.findById(id);
    }

    @Override
    public List<Review> findAllByBook(Book book) { return jpaReviewRepository.findAllByBook(book);}
}
