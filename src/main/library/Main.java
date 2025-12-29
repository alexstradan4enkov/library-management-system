package library;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Добавление книг
        library.addBook(new Book(1, "Война и мир",
                "Л.Н. Толстой", 1869, "978-5-17-090335-2"));
        library.addBook(new Book(2, "Преступление и наказание",
                "Ф.М. Достоевский", 1866, "978-5-17-090336-9"));
        library.addBook(new Book(3, "Анна Каренина",
                "Л.Н. Толстой", 1877, "978-5-17-090337-6"));

        //поиск по ID
        System.out.println("поиск книги с ID = 2:");
        Book bookById = library.findBookById(2);
        if (bookById != null) {
            System.out.println(bookById);
        } else {
            System.out.println("книга не найдена.");
        }

        //поиск по автору
        System.out.println("\nкниги автора Л.Н. Толстой:");
        List<Book> tolstoyBooks = library.findBooksByAuthor("Л.Н. Толстой");
        for (Book b : tolstoyBooks) {
            System.out.println(b);
            System.out.println();
        }

        //выдача книги
        System.out.println("выдача книги с ID = 1:");
        boolean borrowed = library.borrowBook(1);
        System.out.println("результат выдачи: " + borrowed);

        //попытка выдать ту же книгу ещё раз
        System.out.println("повторная выдача той же книги:");
        boolean borrowedAgain = library.borrowBook(1);
        System.out.println("результат повторной выдачи: " + borrowedAgain);

        //возврат книги
        System.out.println("возврат книги с ID = 1:");
        boolean returned = library.returnBook(1);
        System.out.println("результат возврата: " + returned);

        //список доступных книг
        System.out.println("\nдоступные книги:");
        List<Book> availableBooks = library.getAvailableBooks();
        for (Book b : availableBooks) {
            System.out.println(b);
            System.out.println();
        }

        //вывод журнала операций
        System.out.println("журнал операций:");
        library.printOperationLog();
    }
}
