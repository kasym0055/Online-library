package src.models;

public class User {
// kalaas
    private int id;
    private String name;
    private String email;
    public User(int id, String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String email) {
        setName(name);
        setEmail(email);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
