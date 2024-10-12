package goorm.unit.booklog.domain.book.domain;

import goorm.unit.booklog.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> findByTitleAndAuthor(String title, String author);

    List<Book> findAll();
}
