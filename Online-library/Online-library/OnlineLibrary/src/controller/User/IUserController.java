package src.controller.User;

public interface IUserController {
    String register(String name, String surname);
    String getUser(int id);
}
