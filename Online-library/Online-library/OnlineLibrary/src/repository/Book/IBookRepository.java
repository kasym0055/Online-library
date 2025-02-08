package src.repository.Book;

import src.models.Book;

import java.util.List;

public interface IBookRepository {
    void create(Book book);
    void issueBook(int bookId, int userId);
    List<String> viewBooks();
}
