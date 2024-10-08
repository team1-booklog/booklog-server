package goorm.unit.booklog.domain.user.presentation;

import goorm.unit.booklog.domain.user.application.UserService;
import goorm.unit.booklog.domain.user.presentation.request.UserCreateRequest;
import goorm.unit.booklog.domain.user.presentation.response.UserPersistResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", "USERNAME_DUPLICATE");
        errorResponse.put("message", ex.getMessage());  // 예외 메시지 사용 ("이미 존재하는 아이디입니다.")
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}

