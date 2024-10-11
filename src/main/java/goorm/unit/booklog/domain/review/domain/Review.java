package goorm.unit.booklog.domain.review.domain;

import static goorm.unit.booklog.domain.review.domain.ReviewStatus.ACTIVE;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import goorm.unit.booklog.common.domain.BaseTimeEntity;
import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.file.domain.File;
import goorm.unit.booklog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    public static Review create( String title, String content, File file, User user, Book book) {
        return Review.builder()
            .title(title)
            .content(content)
            .status(ACTIVE)
            .file(file)
            .user(user)
            .book(book)
            .build();
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateFile(File file) {
        this.file = file;
    }

    public void updateStatus(ReviewStatus status) {
        this.status = status;
    }
}
