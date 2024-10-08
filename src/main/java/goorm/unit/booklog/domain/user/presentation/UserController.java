package goorm.unit.booklog.domain.user.presentation;

import goorm.unit.booklog.domain.user.application.UserService;
import goorm.unit.booklog.domain.user.presentation.request.UserCreateRequest;
import goorm.unit.booklog.domain.user.presentation.response.UserPersistResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")  // 기본 경로 설정
@RequiredArgsConstructor   // 의존성 주입을 위한 Lombok 어노테이션
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserPersistResponse> createUser(
            @Valid @RequestBody UserCreateRequest request  // 요청 데이터를 검증 및 매핑
    ) {
        String userId = userService.createUser(request);
        return new ResponseEntity<>(UserPersistResponse.of(userId), HttpStatus.CREATED);
    }
}

