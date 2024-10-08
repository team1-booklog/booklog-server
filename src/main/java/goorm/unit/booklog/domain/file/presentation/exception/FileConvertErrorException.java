package goorm.unit.booklog.domain.file.presentation.exception;

import static goorm.unit.booklog.domain.file.presentation.exception.FileExceptionCode.FILE_CONVERT_ERROR;

import goorm.unit.booklog.common.exception.CustomException;

public class FileConvertErrorException extends CustomException {

	public FileConvertErrorException() {
		super(FILE_CONVERT_ERROR);
	}
}
