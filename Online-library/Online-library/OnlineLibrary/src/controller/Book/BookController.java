package src.controller.Book;

import src.models.Book;
import src.repository.Book.IBookRepository;

import java.util.Optional;

public class BookController implements IBookController {
    private final IBookRepository repo;

    public BookController(IBookRepository repo) {
        this.repo = repo;
    }

    
    public void addBook(String title, String author) {
        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            System.out.println("Ошибка: Название и автор книги не могут быть пустыми!");
            return;
        }

        Book book = new Book(title, author); 
        repo.create(book);
    }

  
    public void issueBook(int bookId, int userId) {
        if (bookId <= 0 || userId <= 0) {
            System.out.println("Ошибка: ID книги и пользователя должны быть положительными числами!");
            return;
        }

        repo.issueBook(bookId, userId);
    }

    
    public void viewBooks() {
        repo.viewBooks();
    }

    
}
