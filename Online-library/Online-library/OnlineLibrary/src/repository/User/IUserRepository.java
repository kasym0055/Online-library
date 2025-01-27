package src.repository.User;

import src.models.User;

public interface IUserRepository {
    Boolean register(User user);
    void login();
    User getUser(int id);
}
