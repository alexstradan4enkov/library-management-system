package library;
public class Book {
    private int id;
    private string title;
    private string author;
    private int year;
    private string isbn;
    private boolean available;

    public Book(int id, string title, string author, int year, string isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.available = true;
    }

    // геттеры
    public int getId() { return id; }
    public string getTitle() { return title; }
    public string getAuthor() { return author; }
    public int getYear() { return year; }
    public string getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }

    // сеттер
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public string toString() {
        return String.format("[ID: %d] \"%s\" — %s (%d)\nISBN: %s | Статус: %s",
                id, title, author, year, isbn, available ? "доступна" : "выдана");
    }
}
