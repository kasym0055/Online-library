package src.controller.Book;

import src.models.Book;
import src.repository.Book.IBookRepository;

import java.util.List;
import java.util.Optional;

public class BookController implements IBookController {
    private final IBookRepository repo;

    public BookController(IBookRepository repo) {
        this.repo = repo;
    }

    // Метод для добавления книги
    public void addBook(String title, String author) {
        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            System.out.println("Ошибка: Название и автор книги не могут быть пустыми!");
            return;
        }

        Book book = new Book(title, author); // ID и доступность книги
        repo.create(book);
    }

    // Метод для выдачи книги
    public void issueBook(int bookId, int userId) {
        if (bookId <= 0 || userId <= 0) {
            System.out.println("Ошибка: ID книги и пользователя должны быть положительными числами!");
            return;
        }

        repo.issueBook(bookId, userId);
    }

    // Метод для просмотра всех книг
    public List<String> viewBooks() {
        return repo.viewBooks();
    }

    // Метод для получения книги по ID
//    public void getBookById(int bookId) {
//        Optional<Book> book = repo.findById(bookId); // Требуется метод findById в IBookRepository
//        if (book.isPresent()) {
//            System.out.println("Книга найдена: " + book.get());
//        } else {
//            System.out.println("Ошибка: Книга с ID " + bookId + " не найдена!");
//        }
//    }
}
