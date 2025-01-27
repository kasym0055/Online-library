package src.controller.Book;

public interface IBookController {
    void addBook(String title, String author);
    void viewBooks();
    void issueBook(int bookId, int userId);
}
