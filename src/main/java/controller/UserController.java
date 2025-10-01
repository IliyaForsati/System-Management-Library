//package controller;
//
//import services.UserService;
//import services.interfaces.IUserService;
//
//public class UserController {
//    private final static IUserService service = UserService.getInstance();
//    public static ResultDTO run(String[] args) {
//        try {
//            String command = args[0];
//            switch (command) {
//                case "borrow" -> {
//                    return service.borrow(Integer.parseInt(args[1]));
//                }
//                case "return" -> {
//                    return service.return_(Integer.parseInt(args[1]));
//                }
//                case "show" -> {
//                    return service.ShowHistory();
//                }
//                default -> {
//                    return new ResultDTO("invalid command", false);
//                }
//            }
//        } catch (Exception e) {
//            return new ResultDTO("invalid command!", false);
//        }
//    }
//}
