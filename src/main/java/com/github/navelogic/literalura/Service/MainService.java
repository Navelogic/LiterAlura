package com.github.navelogic.literalura.Service;

import com.github.navelogic.literalura.Model.Author;
import com.github.navelogic.literalura.Model.Language;
import com.github.navelogic.literalura.Repository.AuthorRepository;
import com.github.navelogic.literalura.Repository.BookRepository;
import com.github.navelogic.literalura.Util.DTO.AuthorDTO;
import com.github.navelogic.literalura.Util.DTO.BookDTO;
import com.github.navelogic.literalura.Util.DTO.SearchResultDTO;
import org.springframework.stereotype.Service;

import com.github.navelogic.literalura.Model.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class MainService {
    private final Scanner scanner = new Scanner(System.in);

    private final ApiConsumer apiConsumer;
    private final DataConverter dataConverter;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private static final String API_BASE_URL = "https://gutendex.com/books/?search=";

    public MainService(ApiConsumer apiConsumer, DataConverter dataConverter, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.apiConsumer = apiConsumer;
        this.dataConverter = dataConverter;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public void showMenu() {
        var option = -1;
        while (option != 0) {
            String menu = """
                    
                    *********************************
                    Escolha uma das opções abaixo:
                    1 - Buscar livro por título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros por idioma
                    
                    0 - Sair
                    *********************************
                    """;
            System.out.println(menu);
            while (!scanner.hasNextInt()) {
                System.out.println("Formato inválido. Por favor, insira um número do menu.");
                scanner.nextLine();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> searchBookByTitle();
                case 2 -> listRegisteredBooks();
                case 3 -> listRegisteredAuthors();
                case 4 -> listAuthorsAliveInYear();
                case 5 -> listBooksByLanguage();
                case 0 -> System.out.println("Fechando a aplicação...");
                default -> System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }


    private void searchBookByTitle() {
        System.out.println("Digite o nome do livro que você deseja buscar:");
        var bookTitle = scanner.nextLine();
        var json = apiConsumer.fetchData(API_BASE_URL + bookTitle.replace(" ", "%20"));
        SearchResultDTO searchResult = dataConverter.getData(json, SearchResultDTO.class);

        if (searchResult != null && searchResult.results() != null && !searchResult.results().isEmpty()) {
            BookDTO bookDTO = searchResult.results().get(0);

            Optional<Book> existingBook = bookRepository.findByTitleContainingIgnoreCase(bookDTO.title());
            if (existingBook.isPresent()) {
                System.out.println("\nEste livro já está registrado no banco de dados.");
                return;
            }

            Book book = new Book(bookDTO);

            if (bookDTO.authors() != null && !bookDTO.authors().isEmpty()) {
                AuthorDTO authorDTO = bookDTO.authors().get(0);
                Author author = authorRepository.findByNameContainingIgnoreCase(authorDTO.name())
                        .orElseGet(() -> authorRepository.save(new Author(authorDTO)));

                book.setAuthor(author);
                bookRepository.save(book);
                System.out.println("\nLivro registrado com sucesso!");
                System.out.println(book);
            } else {
                System.out.println("Livro encontrado, mas não possui autor. Não foi possível registrar.");
            }
        } else {
            System.out.println("Nenhum livro encontrado com esse título.");
        }
    }

    private void listRegisteredBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("Nenhum livro foi registrado ainda.");
        } else {
            System.out.println("\n--- Livros Registrados ---");
            books.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .forEach(System.out::println);
        }
    }

    private void listRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor foi registrado ainda.");
        } else {
            System.out.println("\n--- Autores Registrados ---");
            authors.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .forEach(System.out::println);
        }
    }

    private void listAuthorsAliveInYear() {
        System.out.println("Digite o ano para buscar autores vivos:");
        while (!scanner.hasNextInt()) {
            System.out.println("Formato inválido. Por favor, digite um ano válido (ex: 1850).");
            scanner.nextLine();
        }
        var year = scanner.nextInt();
        scanner.nextLine();

        List<Author> authors = authorRepository.findAuthorsAliveInYear(year);
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor vivo foi encontrado no ano de " + year + ".");
        } else {
            System.out.println("\n--- Autores Vivos em " + year + " ---");
            authors.forEach(System.out::println);
        }
    }

    private void listBooksByLanguage() {
        String languageMenu = """
                Selecione o idioma para a busca:
                1. Inglês (en)
                2. Espanhol (es)
                3. Francês (fr)
                4. Português (pt)
                """;
        System.out.println(languageMenu);

        while (!scanner.hasNextInt()) {
            System.out.println("Formato inválido, insira um número do menu.");
            scanner.nextLine();
        }
        int option = scanner.nextInt();
        scanner.nextLine();

        Language language = null;
        switch (option) {
            case 1 -> language = Language.ENGLISH;
            case 2 -> language = Language.SPANISH;
            case 3 -> language = Language.FRENCH;
            case 4 -> language = Language.PORTUGUESE;
            default -> {
                System.out.println("Opção de idioma inválida.");
                return;
            }
        }

        List<Book> books = bookRepository.findByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma: " + language.getFullName());
        } else {
            System.out.println("\n--- Livros em " + language.getFullName() + " ---");
            books.forEach(System.out::println);
        }
    }
}