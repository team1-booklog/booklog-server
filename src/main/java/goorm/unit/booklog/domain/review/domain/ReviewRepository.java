package goorm.unit.booklog.domain.review.domain;

import goorm.unit.booklog.domain.book.domain.Book;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);

    Optional<Review> findById(Long id);

    List<Review> findAllByBook(Book book);
}
