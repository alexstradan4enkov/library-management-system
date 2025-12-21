package library;

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

        // Демонстрация всех методов

        System.out.println("=== Демонстрация работы библиотеки ===\n");

        // 1. Поиск книг по автору
        System.out.println("1. Книги Л.Н. Толстого:");
        for (Book book : library.findBooksByAuthor("Л.Н. Толстой")) {
            System.out.println("   " + book);
        }

        // 2. Выдача книги
        System.out.println("\n2. Выдаем книгу с ID=1:");
        library.borrowBook(1);
        System.out.println("   После выдачи: " + library.findBookById(1));

        // 3. Доступные книги
        System.out.println("\n3. Доступные книги сейчас:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println("   " + book.getTitle());
        }

        // 4. Возврат книги
        System.out.println("\n4. Возвращаем книгу с ID=1:");
        library.returnBook(1);
        System.out.println("   После возврата: " + library.findBookById(1));

        // 5. Статистика
        System.out.println("\n5. Статистика библиотеки:");
        System.out.println("   " + library.getStatistics());

        // 6. Журнал всех операций
        System.out.prirationLog();

        System.out.println("\n=== Демонстрация завершена ===");
    }
}