package goorm.unit.booklog.domain.file.application;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
	String upload(MultipartFile file, String originalFileName);

	default String getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
	}

	default String generateUniqueFileName(String fileExtension) {
		return UUID.randomUUID() + fileExtension;
	}

}
