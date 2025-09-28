package services;

import model.Magazine;
import model.Publication;
import model.ResultDTO;
import model.enums.Status;
import model.enums.Type;
import services.interfaces.IMagazineService;

import java.time.Year;

public class MagazineService
        extends PublicationService<Magazine>
        implements IMagazineService
{
    private static MagazineService instance = null;
    private MagazineService() {}
    public static MagazineService getInstance() {
        if (instance != null) return instance;

        instance = new MagazineService();
        return instance;
    }

    public ResultDTO add(String title, String author, String year, String status) {
        Magazine.MagazineBuilder builder = new Magazine.MagazineBuilder();
        Magazine magazine = builder.title(title)
                .author(author)
                .type(Type.BOOK)
                .publicationYear(Year.parse(year))
                .status(Status.valueOf(status))
                .build();
        return add(magazine);
    }
    public ResultDTO update(String dist_id, String title, String author, String year, String status) {
        Publication dist_parent = Publication.findById(Integer.parseInt(dist_id));
        Magazine dist = null;
        if (dist_parent instanceof Magazine)
            dist = (Magazine) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        Magazine.MagazineBuilder builder = new Magazine.MagazineBuilder();
        Magazine magazine = builder.title(title)
                .author(author)
                .type(Type.BOOK)
                .publicationYear(Year.parse(year))
                .status(Status.valueOf(status))
                .build();

        return update(dist, magazine);
    }
    public ResultDTO remove(String id) {
        Publication dist_parent = Publication.findById(Integer.parseInt(id));
        Magazine dist = null;
        if (dist_parent instanceof Magazine)
            dist = (Magazine) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        return remove(dist);
    }
    public ResultDTO display(String id) {
        Publication dist_parent = Publication.findById(Integer.parseInt(id));
        Magazine dist = null;
        if (dist_parent instanceof Magazine)
            dist = (Magazine) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        return display(dist);
    }
}
