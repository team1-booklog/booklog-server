package goorm.unit.booklog.domain.book.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import goorm.unit.booklog.domain.book.domain.Book;

public interface JpaBookRepository extends JpaRepository<Book, String> {
}
