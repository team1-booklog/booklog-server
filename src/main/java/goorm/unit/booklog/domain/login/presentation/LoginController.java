package goorm.unit.booklog.domain.login.presentation;

import goorm.unit.booklog.domain.login.application.LoginService;
import goorm.unit.booklog.domain.login.presentation.request.LoginRequest;
import goorm.unit.booklog.domain.login.presentation.request.RefreshTokenRequest;
import goorm.unit.booklog.domain.login.presentation.response.AccessTokenResponse;
import goorm.unit.booklog.domain.login.presentation.response.LoginSucceedResponse;
import goorm.unit.booklog.common.exception.ExceptionResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Login", description = "로그인 api / 담당자 : 장선우")
public class LoginController {
    private final LoginService loginService;

    @Operation(summary = "로그인", description = "로그인을 수행합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = LoginSucceedResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "비밀번호가 일치하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "유저를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginSucceedResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        LoginSucceedResponse response = loginService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "토큰 재발급", description = "리프레시 토큰을 이용해 새로운 액세스 토큰을 발급합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "토큰 재발급 성공",
                    content = @Content(schema = @Schema(implementation = AccessTokenResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "유효한 토큰을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            )
    })

    @PostMapping("/reissue")
    public ResponseEntity<AccessTokenResponse> reissue(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        AccessTokenResponse response = loginService.reissue(request);
        return ResponseEntity.ok(response);
    }
}
