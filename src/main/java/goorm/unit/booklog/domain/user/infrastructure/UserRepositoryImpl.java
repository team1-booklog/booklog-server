package goorm.unit.booklog.domain.user.infrastructure;

import goorm.unit.booklog.domain.user.domain.User;
import goorm.unit.booklog.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public Boolean existsById(String id) {
        return jpaUserRepository.existsById(id);
    }

}
