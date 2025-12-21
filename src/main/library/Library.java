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