package com.github.navelogic.literalura.Model;

import com.github.navelogic.literalura.Util.DTO.AuthorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();


    public Author(AuthorDTO authorDTO) {
        this.name = authorDTO.name();
        this.birthYear = authorDTO.birthYear();
        this.deathYear = authorDTO.deathYear();
    }

    @Override
    public String toString() {
        String bookTitles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(" | "));

        return """
               ---------- Autor ----------
               Nome: %s
               Ano de Nascimento: %s
               Ano de Falecimento: %s
               Livros: [%s]
               ---------------------------
               """.formatted(
                name,
                birthYear != null ? birthYear : "N/A",
                deathYear != null ? deathYear : "N/A",
                !bookTitles.isEmpty() ? bookTitles : "Nenhum livro registrado"
        );
    }
}
