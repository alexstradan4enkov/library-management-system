package library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private OperationLog operationLog;

    // Конструктор
    public Library() {
        this.books = new ArrayList<>();
        this.operationLog = new OperationLog();
    }

    // Метод добавления книги
    public void addBook(Book book) {
        books.add(book);
        operationLog.addEntry(OperationLog.OperationType.ADD_BOOK, 
            "Добавлена книга: " + book.getTitle());
    }

    // Метод поиска книги по ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    public String getStatistics() {
        int total = books.size();
        int available = getAvailableBooks().size();
        int borrowed = total - available;
        
        return String.format("Всего книг: %d | Доступно: %d | Выдано: %d",
            total, available, borrowed);
    }
     public boolean removeBook(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                Book removed = books.remove(i);
                operationLog.addEntry(OperationLog.OperationType.ADD_BOOK,
                    "Удалена книга: " + removed.getTitle());
                return true;
            }
        }
        return false;
    }
    // Метод поиска книг по автору
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    // Метод выдачи книги
    public void borrowBook(int id) {
        Book book = findBookById(id);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            operationLog.addEntry(OperationLog.OperationType.BORROW,
                "Выдана книга: " + book.getTitle());
        }
    }

    // Метод возврата книги
    public void returnBook(int id) {
        Book book = findBookById(id);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            operationLog.addEntry(OperationLog.OperationType.RETURN,
                "Возвращена книга: " + book.getTitle());
        }
    }

    // Метод получения доступных книг
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    // Метод для вывода журнала операций
    public void printOperationLog() {
        operationLog.printLog();
    }

    // Метод getStatistics (добавлен через cherry-pick)
    public String getStatistics() {
        int total = books.size();
        int available = getAvailableBooks().size();
        int borrowed = total - available;
        
        return String.format("Всего книг: %d | Доступно: %d | Выдано: %d",
            total, available, borrowed);
    }

    // ВЛОЖЕННЫЙ КЛАСС OperationLog
    public static class OperationLog {
        public enum OperationType {
            ADD_BOOK, BORROW, RETURN
        }

        // Внутренний класс для записи операции
        public class LogEntry {
            private OperationType type;
            private LocalDateTime timestamp;
            private String description;

            public LogEntry(OperationType type, String description) {
                this.type = type;
                this.timestamp = LocalDateTime.now();
                this.description = description;
            }

            // Геттеры
            public OperationType getType() { return type; }
            public LocalDateTime getTimestamp() { return timestamp; }
            public String getDescription() { return description; }

            @Override
            public String toString() {
                return String.format("[%s] %s - %s",
                    timestamp.toString(), type.toString(), description);
            }
        }

        private List<LogEntry> entries;

        public OperationLog() {
            this.entries = new ArrayList<>();
        }

        public void addEntry(OperationType type, String description) {
            entries.add(new LogEntry(type, description));
        }

        public List<LogEntry> getEntries() {
            return new ArrayList<>(entries);
        }

        public void printLog() {
            System.out.println("=== Журнал операций ===");
            for (LogEntry entry : entries) {
                System.out.println(entry);
            }
            System.out.println("=======================");
        }
    }
}
