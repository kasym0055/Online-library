package src;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.controller.Book.BookController;
import src.controller.Book.IBookController;
import src.controller.User.IUserController;
import src.controller.User.UserController;
import src.data.DBConnection;
import src.data.IDBConnection;
import src.repository.Book.BookRepository;
import src.repository.Book.IBookRepository;
import src.repository.User.IUserRepository;
import src.repository.User.UserRepository;

import java.util.List;

public class Main extends Application {
    private IBookController bookController;
    private IUserController userController;
    private Stage primaryStage;
    private String loggedInUserEmail;
    private static final String ADMIN_EMAIL = "admin@gmail.com";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        IDBConnection db = new DBConnection("jdbc:postgresql://localhost:5432", "postgres", "0000", "LibraryDB");
        IBookRepository bookRepo = new BookRepository(db);
        IUserRepository userRepo = new UserRepository(db);
        bookController = new BookController(bookRepo);
        userController = new UserController(userRepo);

        showLoginScreen();
    }

    private void showLoginScreen() {
        primaryStage.setTitle("Авторизация");
        Label userLabel = new Label("Email:");
        TextField userField = new TextField();
        Label passLabel = new Label("Пароль:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Войти");
        Button registerButton = new Button("Регистрация");

        loginButton.setOnAction(e -> {
            if (userController.validateUser(userField.getText(), passField.getText())) {
                loggedInUserEmail = userField.getText();
                showMenuScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Неверные учетные данные");
                alert.show();
            }
        });
        registerButton.setOnAction(e -> showRegisterScreen());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(userLabel, userField, passLabel, passField, loginButton, registerButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showRegisterScreen() {
        primaryStage.setTitle("Регистрация");
        Label nameLabel = new Label("Имя:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label passLabel = new Label("Пароль:");
        PasswordField passField = new PasswordField();
        Button registerButton = new Button("Зарегистрироваться");
        Button backButton = new Button("Назад");

        registerButton.setOnAction(e -> {
            userController.register(nameField.getText(), emailField.getText(), passField.getText());
            showLoginScreen();
        });
        backButton.setOnAction(e -> showLoginScreen());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(nameLabel, nameField, emailLabel, emailField, passLabel, passField, registerButton, backButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMenuScreen() {
        primaryStage.setTitle("Меню");
        Button issueBookButton = new Button("Выдать книгу");
        Button viewBooksButton = new Button("Посмотреть список книг");
        Button logoutButton = new Button("Выход");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        if (ADMIN_EMAIL.equals(loggedInUserEmail)) {
            Button addBookButton = new Button("Добавить книгу");
            addBookButton.setOnAction(e -> showAddBookScreen());
            layout.getChildren().add(addBookButton);
        }

        issueBookButton.setOnAction(e -> showIssueBookScreen());
        viewBooksButton.setOnAction(e -> showViewBooksScreen());
        logoutButton.setOnAction(e -> showLoginScreen());

        layout.getChildren().addAll(issueBookButton, viewBooksButton, logoutButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddBookScreen() {
        primaryStage.setTitle("Добавить книгу");
        Label titleLabel = new Label("Название книги:");
        TextField titleField = new TextField();
        Label authorLabel = new Label("Автор:");
        TextField authorField = new TextField();
        Button addBookButton = new Button("Добавить");
        Button backButton = new Button("Назад");

        addBookButton.setOnAction(e -> {
            bookController.addBook(titleField.getText(), authorField.getText());
            titleField.clear();
            authorField.clear();
        });
        backButton.setOnAction(e -> showMenuScreen());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, titleField, authorLabel, authorField, addBookButton, backButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showIssueBookScreen() {
        primaryStage.setTitle("Выдать книгу");
        Label bookIdLabel = new Label("ID книги:");
        TextField bookIdField = new TextField();
        Label userIdLabel = new Label("ID пользователя:");
        TextField userIdField = new TextField();
        Button issueButton = new Button("Выдать");
        Button backButton = new Button("Назад");

        issueButton.setOnAction(e -> bookController.issueBook(Integer.parseInt(bookIdField.getText()), Integer.parseInt(userIdField.getText())));
        backButton.setOnAction(e -> showMenuScreen());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(bookIdLabel, bookIdField, userIdLabel, userIdField, issueButton, backButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showViewBooksScreen() {
        primaryStage.setTitle("Список книг");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        List<String> books = bookController.viewBooks();
        if (books.isEmpty()) {
            layout.getChildren().add(new Label("Список книг пуст"));
        } else {
            for (String book : books) {
                layout.getChildren().add(new Label(book));
            }
        }

        Button backButton = new Button("Назад");
        backButton.setOnAction(e -> showMenuScreen());
        layout.getChildren().add(backButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
