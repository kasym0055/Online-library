package src.repository.Book;

import src.data.IDBConnection;
import src.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {

    private final IDBConnection db;  

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
        
        String issueQuery = "INSERT INTO Issued_Books (book_id, user_id, issue_date) VALUES (?, ?, CURRENT_DATE)";

        try (Connection conn = db.getConnection();
             
             PreparedStatement issueStmt = conn.prepareStatement(issueQuery)){

            
            

            
            issueStmt.setInt(1, bookId);
            issueStmt.setInt(2, userId);
            issueStmt.executeUpdate();


            System.out.println("Книга успешно выдана!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> viewBooks() {
        List<String> books = new ArrayList<>();
        String query = "SELECT title FROM Books";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                books.add(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
