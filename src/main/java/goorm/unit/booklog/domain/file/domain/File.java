package goorm.unit.booklog.domain.file.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import goorm.unit.booklog.common.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String logicalName;

	private String physicalPath;

	public static File of(String logicalName, String filePath) {
		return File.builder()
			.logicalName(logicalName)
			.physicalPath(filePath)
			.build();
	}
}
