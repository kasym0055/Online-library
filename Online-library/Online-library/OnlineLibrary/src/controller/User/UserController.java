package src.controller.User;

import src.models.User;
import src.repository.User.IUserRepository;

import java.util.List;

public class UserController implements IUserController{

    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String register(String name, String email, String password) {
        User user = new User(name, email, password);

        boolean created = repo.register(user);

        return (created ? "User was created!" : "User creation was failed!");
    }


    @Override
    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    @Override
    public boolean validateUser(String email, String password) {
        return repo.validateUser(email, password);
    }

}
