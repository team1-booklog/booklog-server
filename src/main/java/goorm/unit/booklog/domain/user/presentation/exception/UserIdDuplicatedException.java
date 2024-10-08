package goorm.unit.booklog.domain.user.presentation.exception;

import goorm.unit.booklog.common.exception.CustomException;
import static goorm.unit.booklog.domain.user.presentation.exception.UserExceptionCode.USER_ID_DUPLICATED;

public class UserIdDuplicatedException extends CustomException {
    public UserIdDuplicatedException() {
        super(USER_ID_DUPLICATED);
    }
}
