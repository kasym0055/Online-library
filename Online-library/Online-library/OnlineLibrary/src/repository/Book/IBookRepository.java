package src.repository.Book;

import src.models.Book;

public interface IBookRepository {
    void create(Book book);
    void issueBook(int bookId, int userId);
    void viewBooks();
}
