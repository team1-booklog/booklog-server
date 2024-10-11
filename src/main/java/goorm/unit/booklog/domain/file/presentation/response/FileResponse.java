package goorm.unit.booklog.domain.file.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import goorm.unit.booklog.domain.file.domain.File;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FileResponse(

	@Schema(description = "파일 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "파일 이름", example = "example.jpg", requiredMode = REQUIRED)
	String logicalName,

	@Schema(description = "파일 경로", example = "https://example.com", requiredMode = REQUIRED)
	String physicalPath
) {
	public static FileResponse from(File file) {
		return FileResponse.builder()
			.id(file.getId())
			.logicalName(file.getLogicalName())
			.physicalPath("https://kr.object.ncloudstorage.com/booklog-bucket/" + file.getPhysicalPath())
			.build();
	}

	public static FileResponse of(String logicalName, String physicalPath) {
		return FileResponse.builder()
			.logicalName(logicalName + " 대표 이미지")
			.physicalPath(physicalPath)
			.build();
	}

	public static FileResponse of (String title, File file) {
		return FileResponse.builder()
			.logicalName(title + " 대표 이미지")
			.physicalPath(file.getPhysicalPath())
			.build();
	}
}
