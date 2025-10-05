import com.fasterxml.jackson.databind.ObjectMapper;
import controller.*;
import controller.interfaces.*;
import services.*;
import services.interfaces.*;
import settings.network.TCPNetwork;
import settings.serviceProvider.ServiceProvider;
import java.io.*;

public class Program {
    // TCPNetwork setup
    static TCPNetwork network;

    // dependency injection
    public static final ServiceProvider serviceProvider = ServiceProvider.mainScope;
    static {
        // add services
        serviceProvider.addSingleton(IBookService.class, BookService::new);
        serviceProvider.addSingleton(IArticleService.class, ArticleService::new);
        serviceProvider.addSingleton(IMagazineService.class, MagazineService::new);
        serviceProvider.addSingleton(IDissertationService.class, DissertationService::new);
        serviceProvider.addSingleton(IUserService.class, UserService::new);
        serviceProvider.addSingleton(IBorrowService.class, BorrowService::new);

        // add controller
        serviceProvider.addSingleton(IBookController.class, BookController::new);
        serviceProvider.addSingleton(IArticleController.class, ArticleController::new);
        serviceProvider.addSingleton(IMagazineController.class, MagazineController::new);
        serviceProvider.addSingleton(IDissertationController.class, DissertationController::new);
    }

    // mainLoop of program
    private static void mainLoop() throws IOException {
        while (true) {
            String req = network.getRequest();

            try {
                // todo
//                String res = MainController.run(req);
//                network.sendResponse(res);
            } catch (Exception e) {
                network.sendResponse("something went wrong! \n" + e);
            }
        }
    }

    // start of program
    static int port = 8088;
    public static void main(String[] args) throws IOException {
        checkArgs(args);

        network = new TCPNetwork(port);

        mainLoop();
	}
    private static void checkArgs(String[] args) {
        for (String arg : args) {
            if (arg.matches("port=\\d")) {
                port = Integer.parseInt(arg.split("=")[1]);
            }
        }
    }
}
