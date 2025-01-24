package src.repository.Book;

import src.data.IDBConnection;
import src.models.Book;

import java.sql.*;

public class BookRepository implements IBookRepository {

    private final IDBConnection db;  // Dependency Injection

    public BookRepository(IDBConnection db) {
        this.db = db;
    }

    public void create(Book book) {
        String query = "INSERT INTO Books (title, author) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.executeUpdate();
            System.out.println("Книга успешно добавлена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void issueBook(int bookId, int userId) {
        //String checkQuery = "SELECT is_available FROM Books WHERE id = ?";
        String issueQuery = "INSERT INTO Issued_Books (book_id, user_id, issue_date) VALUES (?, ?, CURRENT_DATE)";

        try (Connection conn = db.getConnection();
             //PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement issueStmt = conn.prepareStatement(issueQuery)){

            // Проверяем доступность книги
            //checkStmt.setInt(1, bookId);
            //ResultSet rs = checkStmt.executeQuery();

                // Выдаем книгу
                issueStmt.setInt(1, bookId);
                issueStmt.setInt(2, userId);
                issueStmt.executeUpdate();


                System.out.println("Книга успешно выдана!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        String query = "SELECT * FROM Books";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Название: " + rs.getString("title")
                        + ", Автор: " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
