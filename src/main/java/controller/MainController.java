package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MainController {
    public static String run(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);

        if (map.get("controller").equals("book")) {
            new BookController().run(map.get("command").toString(), map.get("body"));
        }
        return null;
//        else if (map.get("controller").equals("article")) {
//            ArticleController.run(map.get("command"), map.get("body"));
//        }
//        else if (map.get("controller").equals("magazine")) {
//            MagazineController.run(map.get("command"), map.get("body"));
//        }
//        else if (map.get("controller").equals("dissertation")) {
//            DissertationController.run(map.get("command"), map.get("body"));
//        }
    }
}
