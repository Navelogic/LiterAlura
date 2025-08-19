package com.github.navelogic.literalura.Model;

import com.github.navelogic.literalura.Util.DTO.BookDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated(EnumType.STRING)
    private Language language;

    private Integer downloadCount;

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.title();
        if (bookDTO.languages() != null && !bookDTO.languages().isEmpty()) {
            this.language = Language.fromString(bookDTO.languages().get(0));
        }
        this.downloadCount = bookDTO.downloadCount();
    }

    @Override
    public String toString() {
        String authorName = (author != null) ? author.getName() : "Autor Desconhecido";
        return """
               ---------- Livro ----------
               Título: %s
               Autor: %s
               Idioma: %s
               Downloads: %d
               ---------------------------
               """.formatted(title, authorName, language, downloadCount);
    }
}
