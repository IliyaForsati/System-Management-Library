package services.interfaces;

import model.ResultDTO;

public interface IUserService {
    ResultDTO borrow(int id);
    ResultDTO return_(int historyId);
    ResultDTO ShowHistory();
}
