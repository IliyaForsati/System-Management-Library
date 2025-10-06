import com.fasterxml.jackson.databind.ObjectMapper;
import controller.*;
import controller.interfaces.*;
import services.*;
import services.interfaces.*;
import settings.serviceProvider.ServiceProvider;
import java.io.*;
import java.util.Scanner;

public class Program {
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
    private static void mainLoop() {
        Scanner scanner = new Scanner(System.in);
        MainController mainController = new MainController();
        while (true) {
            System.out.print("\n> ");
            String newLine = scanner.nextLine();

            System.out.println(mainController.run(newLine));
        }
    }

    // start of program
    public static void main(String[] args) throws IOException {
        mainLoop();
	}
}
