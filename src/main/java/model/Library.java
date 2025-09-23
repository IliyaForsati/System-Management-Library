package model;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Year;
import java.lang.Exception;
import util.List;
import java.io.*;
import java.util.Objects;

public class Library {
    private static URI filePath ;
    private final List<Book> books = new List<>();

    // constructors
    public Library() {
        addBooks();
    }
    private void addBooks() {
        try {
            filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("test.txt")).toURI();
            Reader r = new FileReader(new File(filePath));
            BufferedReader bf = new BufferedReader(r);

            while (true) {
                String line = bf.readLine();

                if (line == null) break;

                String[] parts = line.split(",");
                Book book = new Book(parts[0], parts[1], Year.parse(parts[2]), Status.valueOf(parts[3]));
                books.append(book);
            }

            bf.close();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // skip
        } catch (IOException | URISyntaxException e) {
            System.err.println(e);
        }
    }
    public ResultDTO saveChanges() {
        try {
            File f = new File(filePath);
            File temp = File.createTempFile("test", ".txt", f.getParentFile());
            FileWriter fw = new FileWriter(temp);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < books.size(); i++) {
                bw.write(books.get(i).getTitle()+","+books.get(i).getAuthor()
                        +","+books.get(i).getYear().toString()+","+books.get(i).getStatus().name());
                bw.newLine();
            }

            bw.close();
            if (f.delete()) {
                temp.renameTo(f);
            }

        } catch (IOException e) {
            return new ResultDTO("something went wrong! " + e, false);
        }

        return new ResultDTO("changes save successfully.", true);
    }

    // create
    private ResultDTO addBook(Book book) {
        books.append(book);
        return new ResultDTO("book: " + book.getTitle() + " added successfully.", true);
    }
    public ResultDTO addBook(String title, String author, Year year, Status status) {
        try {
            return addBook(new Book(title, author, year, status));
        } catch (Exception e) {
            return new ResultDTO(e.getMessage(), false);
        }
    }

    // read
    public List<Book> getBooks() {
        return books;
    }
    public ResultDTO printBooks(SortType type) {
        try {
            if (type != null) {
                sortBooksArray(type, books);
            }

            return new ResultDTO(books.toString(), true);
        } catch (Exception e) {
            return new ResultDTO(e.getMessage(), false);
        }
    }
    public ResultDTO printBooks() {
        return printBooks(null);
    }
    public ResultDTO searchBook(String keyWord, SortType type) {
        try {
            List<Book> filteredBooks = new List<>();

            for (int i = 0; i < books.size(); i++)
                if (books.get(i).getTitle().toLowerCase().contains(keyWord.toLowerCase()) ||
                    books.get(i).getAuthor().toLowerCase().contains(keyWord.toLowerCase()))
                    filteredBooks.append(books.get(i));

            if (type != null)
                sortBooksArray(type, filteredBooks);

            return new ResultDTO(filteredBooks.toString(), true);
        } catch (Exception e) {
            return new ResultDTO(e.getMessage(), false);
        }
    }
    public ResultDTO searchBook(String keyWord) {
        return searchBook(keyWord, null);
    }

    // update
    private ResultDTO updateBook(Book dist, Book src) {
        deepCopyBook(dist, src);
        return new ResultDTO("book: " + dist.getTitle() + " updated successfully.", true);
    }
    public ResultDTO updateBook(String distTitleOrId, Book src) {
        try {
            List<Book> foundBooks = findBookByTitle(distTitleOrId);

            assert foundBooks != null;
            if (foundBooks.size() > 1) {
                ResultDTO result = searchBook(distTitleOrId);
                result.setMessage(result.getMessage() + "\n please use id for this work.");

                return result;
            }

            Book dist = foundBooks.get(0);
            if (dist == null) {
                return new ResultDTO("book: " + distTitleOrId + " not found.", false);
            }
            return updateBook(dist, src);
        } catch (Exception e) {
            return new ResultDTO(e.getMessage(), false);
        }
    }

    // delete
    private ResultDTO removeBook(Book book) {
        if (books.remove(book)) {
            return new ResultDTO("book: " + book.getTitle() + " removed successfully", true);
        }

        return new ResultDTO("book: " + book.getTitle() + " not found. But how!!!", false);
    }
    public ResultDTO removeBook(String titleOrId) {
        try {
            List<Book> foundBooks = findBookByTitle(titleOrId);

            assert foundBooks != null;
            if (foundBooks.size() > 1) {
                ResultDTO result = searchBook(titleOrId);
                result.setMessage(result.getMessage() + "\n please use id for this work.");

                return result;
            }

            Book book = foundBooks.get(0);

            if (book == null) {
                return new ResultDTO("book: " + titleOrId + " not found.", false);
            }
            return removeBook(book);
        } catch (Exception e) {
            return new ResultDTO(e.getMessage(), false);
        }
    }

    // private methods
    private void sortBooksArray(SortType type, List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            for (int j = i + 1; j < books.size(); j++) {
                if (type == SortType.DOWNWARD) {
                    if (books.get(i).getYear().compareTo(books.get(j).getYear()) < 0) {
                        Book temp = books.get(i);
                        books.set(i, books.get(j));
                        books.set(j, temp);
                    }
                }
                else if (type == SortType.UPWARD) {
                    if (books.get(i).getYear().compareTo(books.get(j).getYear()) > 0) {
                        Book temp = books.get(i);
                        books.set(i, books.get(j));
                        books.set(j, temp);
                    }
                }
            }
        }
    }

    private void deepCopyBook(Book dist, Book src) {
        dist.setTitle(src.getTitle());
        dist.setAuthor(src.getAuthor());
        dist.setYear(src.getYear());
        dist.setStatus(src.getStatus());
    }

    private List<Book> findBookByTitle(String titleOrId) {
        List<Book> foundBooks = new List<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(titleOrId) ||
                String.valueOf(books.get(i).getUniqueId()).equals(titleOrId)) {
                foundBooks.append(books.get(i));
            }
        }
        if (foundBooks.size() == 0)
            return null;
        return foundBooks;
    }
}
