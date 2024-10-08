package goorm.unit.booklog.domain.file.presentation.exception;

import static goorm.unit.booklog.domain.file.presentation.exception.FileExceptionCode.OBJECTSTORAGE_SERVER_ERROR;

import goorm.unit.booklog.common.exception.CustomException;

public class ObjectStorageServerException extends CustomException {
	public ObjectStorageServerException() {
		super(OBJECTSTORAGE_SERVER_ERROR);
	}
}
