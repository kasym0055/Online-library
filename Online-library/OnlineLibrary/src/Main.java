package src;
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

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDBConnection db = new DBConnection("jdbc:postgresql://localhost:5432", "postgres", "root", "LibraryDB");
        IUserRepository repo = new UserRepository(db);
        IUserController controller = new UserController(repo);

        IBookRepository repoBook = new BookRepository(db);
        IBookController controllerBook = new BookController(repoBook);

        while (true) {
            System.out.println("\nСистема управления библиотекой");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Зарегистрировать пользователя");
            System.out.println("3. Выдать книгу");
            System.out.println("4. Посмотреть список книг");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите название книги: ");
                    String title = scanner.next();
                    System.out.print("Введите автора книги: ");
                    String author = scanner.next();
                    controllerBook.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Введите имя пользователя: ");
                    String name = scanner.next();
                    System.out.print("Введите email пользователя: ");
                    String email = scanner.next();
                    controller.register(name, email, "male");
                    break;
                case 3:
                    System.out.print("Введите ID книги: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Введите ID пользователя: ");
                    int userId = scanner.nextInt();
                    controllerBook.issueBook(bookId, userId);
                    break;
                case 4:
                    controllerBook.viewBooks();
                    break;
                case 5:
                    System.out.println("Выход из программы...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}

