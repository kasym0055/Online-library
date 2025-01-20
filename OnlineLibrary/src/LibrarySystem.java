package src;

import java.sql.*;

public class LibrarySystem {

    public void addBook(String title, String author) {
        String query = "INSERT INTO Books (title, author) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Книга успешно добавлена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(String name, String email) {
        String query = "INSERT INTO Users (name, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Пользователь успешно зарегистрирован!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void issueBook(int bookId, int userId) {
        String checkQuery = "SELECT is_available FROM Books WHERE id = ?";
        String issueQuery = "INSERT INTO IssuedBooks (book_id, user_id, issue_date) VALUES (?, ?, CURDATE())";
        String updateQuery = "UPDATE Books SET is_available = FALSE WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement issueStmt = conn.prepareStatement(issueQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            // Проверяем доступность книги
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("is_available")) {
                // Выдаем книгу
                issueStmt.setInt(1, bookId);
                issueStmt.setInt(2, userId);
                issueStmt.executeUpdate();

                // Обновляем статус книги
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                System.out.println("Книга успешно выдана!");
            } else {
                System.out.println("Книга недоступна для выдачи.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        String query = "SELECT * FROM Books";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Название: " + rs.getString("title")
                        + ", Автор: " + rs.getString("author")
                        + ", Доступна: " + rs.getBoolean("is_available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
