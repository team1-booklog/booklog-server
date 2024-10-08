package goorm.unit.booklog.domain.user.infrastructure;

import goorm.unit.booklog.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User,String> {
}
