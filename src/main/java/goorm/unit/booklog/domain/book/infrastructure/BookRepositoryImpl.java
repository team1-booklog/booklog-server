package goorm.unit.booklog.domain.book.infrastructure;

import org.springframework.stereotype.Repository;

import goorm.unit.booklog.domain.book.domain.BookRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
	private final JpaBookRepository jpaBookRepository;

}
