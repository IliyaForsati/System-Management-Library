package model;

import java.time.Year;

public class Book {
    private final int uniqueId;
	private String title;
    private String author;
    private Year year;
    private Status status;

    public Book(String title, String author, Year year, Status status) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.status = status;
        this.uniqueId = (title+author).hashCode();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return "id: " + uniqueId + " title: " + title + " author: " + author + " year: "
                + year.toString() + " status: " + status.name();
    }

    @Override
    public boolean equals(Object obj) {
        boolean out = false;
        if (obj instanceof Book) {
            Book input = (Book) obj;
            out = this.title.equals(input.title)
                    && this.author.equals(input.author)
                    && this.year.equals(input.year)
                    && this.status.equals(input.status);
        }

        return out;
    }
}
