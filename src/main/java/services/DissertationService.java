package services;

import model.Dissertation;
import model.Publication;
import model.ResultDTO;
import model.enums.Status;
import model.enums.Type;
import services.interfaces.IDissertationService;

import java.time.Year;

public class DissertationService
        extends PublicationService<Dissertation>
        implements IDissertationService
{
    private static DissertationService instance = null;
    private DissertationService() {}
    public static DissertationService getInstance() {
        if (instance != null) return instance;

        instance = new DissertationService();
        return instance;
    }

    public ResultDTO add(String title, String author, String year, String status) {
        Dissertation.DissertationBuilder builder = new Dissertation.DissertationBuilder();
        Dissertation dissertation = builder.title(title)
                .author(author)
                .type(Type.BOOK)
                .publicationYear(Year.parse(year))
                .status(Status.valueOf(status))
                .build();
        return add(dissertation);
    }
    public ResultDTO update(String dist_id, String title, String author, String year, String status) {
        Publication dist_parent = Publication.findById(Integer.parseInt(dist_id));
        Dissertation dist = null;
        if (dist_parent instanceof Dissertation)
            dist = (Dissertation) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        Dissertation.DissertationBuilder builder = new Dissertation.DissertationBuilder();
        Dissertation dissertation = builder.title(title)
                .author(author)
                .type(Type.BOOK)
                .publicationYear(Year.parse(year))
                .status(Status.valueOf(status))
                .build();

        return update(dist, dissertation);
    }
    public ResultDTO remove(String id) {
        Publication dist_parent = Publication.findById(Integer.parseInt(id));
        Dissertation dist = null;
        if (dist_parent instanceof Dissertation)
            dist = (Dissertation) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        return remove(dist);
    }
    public ResultDTO display(String id) {
        Publication dist_parent = Publication.findById(Integer.parseInt(id));
        Dissertation dist = null;
        if (dist_parent instanceof Dissertation)
            dist = (Dissertation) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        return display(dist);
    }
}
