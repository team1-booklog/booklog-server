package goorm.unit.booklog.domain.user.presentation;

import goorm.unit.booklog.common.exception.ExceptionResponse;
import goorm.unit.booklog.domain.user.application.UserService;
import goorm.unit.booklog.domain.user.presentation.request.UserCreateRequest;
import goorm.unit.booklog.domain.user.presentation.response.UserPersistResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name="User",description="유저 관리 api / 담당자 : 장선우")   // 의존성 주입을 위한 Lombok 어노테이션
public class UserController {
    private final UserService userService;

    @Operation(summary="유저 생성", description="유저를 생성합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode="201",
                    description="유저 생성 성공",
                    content=@Content(schema=@Schema(implementation = UserPersistResponse.class))
            ),
            @ApiResponse(
                    responseCode="409",
                    description="유저 생성 성공",
                    content=@Content(schema=@Schema(implementation = ExceptionResponse.class))
            )
    })
    @ResponseStatus(CREATED)
    @PostMapping("/signup")
    public ResponseEntity<UserPersistResponse> createUser(
            @Valid @RequestBody UserCreateRequest request
    ) {
        UserPersistResponse response = userService.createUser(request);
        return ResponseEntity.status(CREATED).body(response);
    }
}

