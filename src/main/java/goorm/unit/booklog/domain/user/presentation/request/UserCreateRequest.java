package goorm.unit.booklog.domain.user.presentation.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotNull(message = "아이디를 입력해주세요.")
    @Size(min = 4, max = 16, message = "아이디는 4자에서 16자 사이여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 영문 소문자와 숫자로만 구성되어야 합니다.")
    private String id;

    @NotNull(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "비밀번호는 최소 8자리 이상, 영문자와 숫자를 포함해야 합니다.")
    private String password;


    // 생성자
    public UserCreateRequest(String id, String name, String password) {
        this.id = id;
        this.name=name;
        this.password = password;
    }



}

