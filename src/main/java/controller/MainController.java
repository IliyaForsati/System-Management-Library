package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MainController {
    public static String run(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);

        if (map.get("controller").equals("book")) {
            return new BookController().run(map.get("command").toString(), map.get("body"));
        }
        else if (map.get("controller").equals("article")) {
            return new ArticleController().run(map.get("command").toString(), map.get("body"));
        }
        else if (map.get("controller").equals("magazine")) {
            return new MagazineController().run(map.get("command").toString(), map.get("body"));
        }
        else if (map.get("controller").equals("dissertation")) {
            return new DissertationController().run(map.get("command").toString(), map.get("body"));
        }
        return "invalid controller";
    }
}
