package goorm.unit.booklog.domain.user.application;

import goorm.unit.booklog.domain.user.domain.User;
import goorm.unit.booklog.domain.user.presentation.request.UserCreateRequest;
import goorm.unit.booklog.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 아이디 중복 체크
    public boolean isUsernameDuplicate(String id) {
        return userRepository.existsById(id);
    }

    // 회원가입 로직
    public String createUser(UserCreateRequest request) {

        // 아이디 중복 체크
        if (isUsernameDuplicate(request.getId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 비밀번호 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getId(),request.getName(), encodedPassword);
        userRepository.save(user);

        return user.getId();
    }
}

