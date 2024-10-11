package goorm.unit.booklog.domain.book.presentation.exception;

import goorm.unit.booklog.common.exception.CustomException;

import static goorm.unit.booklog.domain.book.presentation.exception.BookExceptionCode.BOOK_NOT_FOUND;

public class BookNotFoundException extends CustomException {
    public BookNotFoundException() {
        super(BOOK_NOT_FOUND);
    }
}
