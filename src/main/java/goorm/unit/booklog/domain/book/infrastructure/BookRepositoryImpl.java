package goorm.unit.booklog.domain.book.infrastructure;

import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.book.domain.BookRepository;
import goorm.unit.booklog.domain.user.domain.User;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository{
    private final JpaBookRepository jpaBookRepository;
    @Override
    public Book save(Book book) {
        return jpaBookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jpaBookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return jpaBookRepository.findByIsbn(isbn);
    }

    @Override
    public Optional<Book> findByTitleAndAuthor(String title, String author) {
        return jpaBookRepository.findByTitleAndAuthor(title, author);
    }
    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll();
    }
}
