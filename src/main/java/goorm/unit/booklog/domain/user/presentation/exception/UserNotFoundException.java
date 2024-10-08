package goorm.unit.booklog.domain.user.presentation.exception;

import goorm.unit.booklog.common.exception.CustomException;
import static goorm.unit.booklog.domain.user.presentation.exception.UserExceptionCode.USER_NOT_FOUND;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
