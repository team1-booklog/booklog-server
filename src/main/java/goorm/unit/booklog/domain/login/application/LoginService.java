package goorm.unit.booklog.domain.login.application;

import java.time.Duration;
import goorm.unit.booklog.common.auth.jwt.TokenProvider;
import goorm.unit.booklog.domain.login.domain.RefreshToken;
import goorm.unit.booklog.domain.login.infrastructure.RefreshTokenRepository;
import goorm.unit.booklog.domain.login.presentation.exception.InvalidPasswordException;
import goorm.unit.booklog.domain.login.presentation.request.LoginRequest;
import goorm.unit.booklog.domain.login.presentation.request.RefreshTokenRequest;
import goorm.unit.booklog.domain.login.presentation.response.AccessTokenResponse;
import goorm.unit.booklog.domain.login.presentation.response.LoginSucceedResponse;
import goorm.unit.booklog.domain.login.presentation.exception.TokenNotFoundException;
import goorm.unit.booklog.domain.user.application.UserService;
import goorm.unit.booklog.domain.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public LoginSucceedResponse login(LoginRequest request) {
        String id = request.id();
        String password = request.password();

        User user = userService.getUserById(id);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException();
        }

        String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(7));
        String accessToken = tokenProvider.generateToken(user, Duration.ofHours(2));
        refreshTokenRepository.save(RefreshToken.of(refreshToken, user.getId()));

        return LoginSucceedResponse.of(accessToken, refreshToken);
    }

    @Transactional
    public AccessTokenResponse reissue(RefreshTokenRequest request) {
        String refreshToken = request.refreshToken();
        RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(TokenNotFoundException::new);

        User user = userService.getUserById(token.getUserId());
        String newAccessToken = tokenProvider.generateToken(user, Duration.ofHours(2));

        return AccessTokenResponse.of(newAccessToken);
    }
}
