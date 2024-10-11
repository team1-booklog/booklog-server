package goorm.unit.booklog.domain.review.infrastructure;

import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByBook(Book book);
}
