package src.repository.User;

import src.data.IDBConnection;
import src.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    private final IDBConnection db;  // Dependency Injection

    public UserRepository(IDBConnection db) {
        this.db = db;
    }

    @Override
    public Boolean register(User us) {
        if (us.getName() == null || us.getName().isEmpty() || us.getEmail() == null || us.getEmail().isEmpty()) {
            System.out.println("Ошибка: Имя и email не могут быть пустыми!");
            return false;
        }

        if (!us.getEmail().contains("@")) {
            System.out.println("Ошибка: Некорректный email!");
            return false;
        }

        String query = "INSERT INTO Users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, us.getName());
            stmt.setString(2, us.getEmail());
            stmt.setString(3, us.getPassword());
            stmt.executeUpdate();
            System.out.println("Пользователь успешно зарегистрирован!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validateUser(String email, String password) {
        String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public User getUser(int id){
//        Connection con = null;
//
//        try {
//            con = db.getConnection();
//            String sql = "SELECT id,name FROM users WHERE id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//
//            st.setInt(1, id);
//
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                return new User(rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("surname"));
//            }
//        } catch (SQLException e) {
//            System.out.println("sql error: " + e.getMessage());
//        }

        return null;
    }
}
