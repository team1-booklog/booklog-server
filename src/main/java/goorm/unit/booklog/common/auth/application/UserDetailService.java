package goorm.unit.booklog.common.auth.application;

import goorm.unit.booklog.domain.user.application.UserService;
import goorm.unit.booklog.domain.user.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public User loadUserByUsername(String id) {
        return userService.getUserById(id);
    }
}