package library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private OperationLog operationLog;

    public Library() {
        books = new ArrayList<>();
        operationLog = new OperationLog();
    }

    public void addBook(Book book) {
        books.add(book);
        operationLog.addEntry(
                OperationLog.OperationType.ADD_BOOK,
                "добавлена книга: " + book.getTitle() + " (ID: " + book.getId() + ")"
        );
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean borrowBook(int id) {
        Book book = findBookById(id);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            operationLog.addEntry(
                    OperationLog.OperationType.BORROW,
                    "книга выдана: " + book.getTitle() + " (ID: " + book.getId() + ")"
            );
            return true;
        }
        return false;
    }

    public boolean returnBook(int id) {
        Book book = findBookById(id);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            operationLog.addEntry(
                    OperationLog.OperationType.RETURN,
                    "книга возвращена: " + book.getTitle() + " (ID: " + book.getId() + ")"
            );
            return true;
        }
        return false;
    }

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    public void printOperationLog() {
        operationLog.printLog();
    }

    //вложенный статический класс
    public static class OperationLog {

        public enum OperationType {
            ADD_BOOK, BORROW, RETURN
        }

        //внутренний класс для записи операции
        public class LogEntry {
            private OperationType type;
            private LocalDateTime timestamp;
            private String description;

            public LogEntry(OperationType type, LocalDateTime timestamp, String description) {
                this.type = type;
                this.timestamp = timestamp;
                this.description = description;
            }

            public OperationType getType() {
                return type;
            }

            public LocalDateTime getTimestamp() {
                return timestamp;
            }

            public String getDescription() {
                return description;
            }

            @Override
            public String toString() {
                return "[" + timestamp + "] " + type + ": " + description;
            }
        }

        private List<LogEntry> entries = new ArrayList<>();

        public void addEntry(OperationType type, String description) {
            LogEntry entry = new LogEntry(type, LocalDateTime.now(), description);
            entries.add(entry);
        }

        public List<LogEntry> getEntries() {
            return entries;
        }

        public void printLog() {
            if (entries.isEmpty()) {
                System.out.println("журнал операций пуст.");
            } else {
                for (LogEntry entry : entries) {
                    System.out.println(entry);
                }
            }
        }
    }
}
