package goorm.unit.booklog.domain.book.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import goorm.unit.booklog.common.domain.BaseTimeEntity;
import goorm.unit.booklog.domain.file.domain.File;
import goorm.unit.booklog.domain.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Book extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	@Column(length = 2000)
	private String description;

	private String isbn;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "file_id")
	private File file;

	@ManyToMany(mappedBy = "books")
	private List<User> users = new ArrayList<>();

	public static Book create(String title, String author, String description, String isbn, File file) {
		return Book.builder()
			.title(title)
			.author(author)
			.description(description)
			.isbn(isbn)
			.file(file)
			.build();
	}
}
