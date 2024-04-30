package library.entity;

public class Literature {
    private String title;
    private String author;
    private Lang lang;

    public Literature (String title, String author, Lang lang) {
        this.title = title;
        this.author = author;
        this.lang = lang;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Lang getLang() { return lang;}

    @Override
    public String toString() {
        return title + " - " + author;
    }
}
