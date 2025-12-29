package library;

// Модель книги
public class Book {

    // Поля
    private int bookId;
    private String name;
    private String writer;
    private int publishYear;
    private String isbnCode;
    private boolean inStock;

    // Конструктор
    public Book(int bookId, String name, String writer, int publishYear, String isbnCode) {
        this.bookId = bookId;
        this.name = name;
        this.writer = writer;
        this.publishYear = publishYear;
        this.isbnCode = isbnCode;
        this.inStock = true;
    }

    // Геттеры
    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getWriter() {
        return writer;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public boolean isInStock() {
        return inStock;
    }

    // Сеттер для статуса наличия
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        String statusText = inStock ? "В наличии" : "Выдана";
        return String.format(
                "Книга #%d: \"%s\" (%d)\nАвтор: %s\nISBN: %s\nСтатус: %s",
                bookId, name, publishYear, writer, isbnCode, statusText
        );
    }
}
