package src.repository.User;

import src.models.User;

public interface IUserRepository {
    Boolean register(User user);
    boolean validateUser(String email, String password);
    User getUser(int id);
}
