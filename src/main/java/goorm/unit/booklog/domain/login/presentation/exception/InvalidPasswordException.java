package goorm.unit.booklog.domain.login.presentation.exception;

import goorm.unit.booklog.common.exception.CustomException;
import static goorm.unit.booklog.domain.login.presentation.exception.LoginExceptionCode.INVALID_PASSWORD;

public class InvalidPasswordException extends CustomException {
    public InvalidPasswordException() {
        super(INVALID_PASSWORD);
    }
}
