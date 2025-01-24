package src.repository.User;

import src.data.IDBConnection;
import src.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    private final IDBConnection db;  

    public UserRepository(IDBConnection db) {
        this.db = db;
    }

    @Override
    public Boolean register(User us) { {
            String query = "INSERT INTO Users (name, email) VALUES (?, ?)";
            try (Connection conn = db.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, us.getName());
                stmt.setString(2, us.getEmail());
                stmt.executeUpdate();
                System.out.println("Пользователь успешно зарегистрирован!");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }



    @Override
    public void login() {

    }

    @Override
    public User getUser(int id){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
