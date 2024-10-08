package goorm.unit.booklog.domain.file.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import goorm.unit.booklog.domain.file.domain.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
