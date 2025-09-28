package model;

import model.enums.Status;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class History {
    private final int id = new Random().nextInt(1000, 9999);
    private Publication publication;
    private LocalDate start;
    private LocalDate end = null;
    public static final ArrayList<History> allHistory = new ArrayList<>();

    private History() {}

    public static int start(Publication publication) {
        History history = new History();
        history.publication = publication;
        history.start = LocalDate.now();
        allHistory.add(history);

        publication.status = Status.BORROWED;
        return history.id;
    }
    public static boolean end(int id) {
        History history = findById(id);

        if (history == null)
            return false;

        history.end = LocalDate.now();
        history.publication.status = Status.EXIST;
        return true;
    }

    private static History findById(int id) {
        for (History history : allHistory) {
            if (history.id == id)
                return history;
        }

        return null;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", publication=" + publication +
                ", start=" + start +
                ", end=" + end;
    }

    public Publication getPublication() {
        return publication;
    }
}
