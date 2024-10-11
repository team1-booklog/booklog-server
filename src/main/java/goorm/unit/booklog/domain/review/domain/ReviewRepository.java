package goorm.unit.booklog.domain.review.domain;

import goorm.unit.booklog.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);

    Optional<Review> findById(Long id);

}
