package goorm.unit.booklog.domain.user.domain;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String id);
    Boolean existsById(String id);
}
