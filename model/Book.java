package model;

import java.time.Year;

public class Book {
	private String title;
    private String author;
    private Year year;
    private Status status;

    public Book(String title, String author, Year year, Status status) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.status = status;
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
}
