package src;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nСистема управления библиотекой");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Зарегистрировать пользователя");
            System.out.println("3. Выдать книгу");
            System.out.println("4. Посмотреть список книг");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите название книги: ");
                    String title = scanner.next();
                    System.out.print("Введите автора книги: ");
                    String author = scanner.next();
                    librarySystem.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Введите имя пользователя: ");
                    String name = scanner.next();
                    System.out.print("Введите email пользователя: ");
                    String email = scanner.next();
                    librarySystem.registerUser(name, email);
                    break;
                case 3:
                    System.out.print("Введите ID книги: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Введите ID пользователя: ");
                    int userId = scanner.nextInt();
                    librarySystem.issueBook(bookId, userId);
                    break;
                case 4:
                    librarySystem.viewBooks();
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

