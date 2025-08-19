package com.github.navelogic.literalura.Repository;

import com.github.navelogic.literalura.Model.Book;
import com.github.navelogic.literalura.Model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByLanguage(Language language);
}
