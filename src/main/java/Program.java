import com.fasterxml.jackson.databind.ObjectMapper;
import controller.MainController;
import services.ArticleService;
import services.BookService;
import services.MagazineService;
import settings.network.TCPNetwork;
import settings.serviceProvider.ServiceProvider;
import java.io.*;

public class Program {
    // TCPNetwork setup
    static TCPNetwork network;

    // jackson setup
    public final static ObjectMapper mapper = new ObjectMapper();

    // dependency injection
    public static final ServiceProvider serviceProvider = ServiceProvider.mainScope;
    static {
        serviceProvider.addSingleton(BookService.class, BookService::new);
        serviceProvider.addSingleton(ArticleService.class, ArticleService::new);
        serviceProvider.addSingleton(MagazineService.class, MagazineService::new);
//        serviceProvider.addSingleton(UserService.class, UserService::new);
    }

    // mainLoop of program
    private static void mainLoop() throws IOException {
        while (true) {
            String req = network.getRequest();

            try {
                String res = MainController.run(req);
                network.sendResponse(res);
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
