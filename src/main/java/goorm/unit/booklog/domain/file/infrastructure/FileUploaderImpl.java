package goorm.unit.booklog.domain.file.infrastructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import goorm.unit.booklog.domain.file.application.FileUploader;
import goorm.unit.booklog.domain.file.presentation.exception.ObjectStorageServerException;
import goorm.unit.booklog.domain.file.presentation.exception.FileConvertErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploaderImpl implements FileUploader {

	@Value("${cloud.ncp.object-storage.credentials.bucket}")
	private String bucketName;

	private final AmazonS3 amazonS3;

	@Override
	public String upload(MultipartFile multipartFile, String logicalName) {
		try {
			File uploadFile = convert(multipartFile)
				.orElseThrow(FileConvertErrorException::new);
			String filePath = generateUploadPath(logicalName);
			amazonS3.putObject(
				new PutObjectRequest(bucketName, filePath, uploadFile)
					.withCannedAcl(CannedAccessControlList.PublicRead)
			);
			removeNewFile(uploadFile);
			return filePath;
		} catch (ObjectStorageServerException e) {
			throw new ObjectStorageServerException();
		}
	}

	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			log.info("File deleted.");
		} else {
			log.info("File could not deleted");
		}
	}

	private Optional<File> convert(MultipartFile file) {
		File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
		try {
			if (convertFile.createNewFile()) {
				try (FileOutputStream fos = new FileOutputStream(convertFile)) {
					fos.write(file.getBytes());
				}
				return Optional.of(convertFile);
			}
		} catch (IOException e) {
			throw new FileConvertErrorException();
		}
		return Optional.empty();
	}

	private String generateUploadPath(String originalFileName) {
		String fileExtension = getFileExtension(originalFileName);
		String fileName = generateUniqueFileName(fileExtension);
		return Path.of(fileName).toString();
	}
}
