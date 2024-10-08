package goorm.unit.booklog.domain.file.presentation.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;

import goorm.unit.booklog.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileExceptionCode implements ExceptionCode {

	FILE_CONVERT_ERROR(BAD_REQUEST, "파일 변환 중 오류가 발생 하였습니다."),
	OBJECTSTORAGE_SERVER_ERROR(INTERNAL_SERVER_ERROR, "파일 처리 중 서버 에러가 발생했습니다.");
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}

