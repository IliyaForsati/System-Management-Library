package model;

import java.time.LocalDate;
import java.util.Random;

public class BorrowHistory {
    private final int id = new Random().nextInt(1000, 9999);
    private Publication publication;
    private LocalDate start;
    private LocalDate end;

    @Override
    public String toString() {
        return  "id=" + id +
                ", publication=" + publication +
                ", start=" + start +
                ", end=" + end;
    }
}
