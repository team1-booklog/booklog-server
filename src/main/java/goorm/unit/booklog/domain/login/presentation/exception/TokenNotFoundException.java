package goorm.unit.booklog.domain.login.presentation.exception;

import goorm.unit.booklog.common.exception.CustomException;
import static goorm.unit.booklog.domain.login.presentation.exception.LoginExceptionCode.TOKEN_NOT_FOUND;

public class TokenNotFoundException extends CustomException {
    public TokenNotFoundException() {
        super(TOKEN_NOT_FOUND);
    }
}
