package src.controller.Book;

import java.util.List;

public interface IBookController {
    void addBook(String title, String author);
    List<String> viewBooks();
    void issueBook(int bookId, int userId);
}
