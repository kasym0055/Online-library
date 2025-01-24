package src.controller.User;

public interface IUserController {
    String register(String name, String surname, String gender);
    String getUser(int id);
}
