package services;

import model.History;
import model.Publication;
import services.interfaces.IUserService;
import model.ResultDTO;

import java.util.ArrayList;

public class UserService implements IUserService {
    private static UserService instance = null;
    private UserService() {}
    public static UserService getInstance() {
        if (instance != null) return instance;

        instance = new UserService();
        return instance;
    }

    public ResultDTO borrow(int id) {
        Publication publication = Publication.findById(id);

        int historyId = History.start(publication);

        return new ResultDTO("id: '" + historyId + "' borrowed", true);
    }

    public ResultDTO return_(int historyId) {
        boolean isSuccessful = History.end(historyId);
        return new ResultDTO(isSuccessful ? "returned successfully" : "error", isSuccessful);
    }

    public ResultDTO ShowHistory() {
        ArrayList<History> allHistory = History.allHistory;

        StringBuilder sb = new StringBuilder();
        for (History history : allHistory) {
            sb.append(history.toString()).append('\n');
        }

        return new ResultDTO(sb.toString(), true);
    }
}
