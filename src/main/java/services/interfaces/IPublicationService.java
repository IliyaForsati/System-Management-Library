package services.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Publication;
import model.enums.SortType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface IPublicationService<T extends Publication> {
    boolean add(T entity);
    T getById(int id);
    ArrayList<T> getAll(SortType st);
    ArrayList<T> search(String key, SortType st);
    boolean update(int distId, T src);
    boolean remove(int entityId);
    static Publication findPublicationById(int id) {
        File publicationsDir = new File("data/publications");

        // Check if publications directory exists
        if (!publicationsDir.exists() || !publicationsDir.isDirectory()) {
            return null;
        }

        // Get all JSON files in the publications directory
        File[] publicationFiles = publicationsDir.listFiles((dir, name) -> name.endsWith(".json"));
        ObjectMapper mapper = new ObjectMapper();

        if (publicationFiles != null) {
            for (File file : publicationFiles) {
                try {
                    // Read the JSON file into an array of Publications
                    Publication[] publications = mapper.readValue(file, Publication[].class);

                    // Search for the publication with the matching ID
                    for (Publication pub : publications) {
                        if (pub != null && pub.getId() == id) {
                            return pub;
                        }
                    }
                } catch (IOException e) {
                    // Log the error and continue with the next file
                    System.err.println("Error reading file: " + file.getName() + ": " + e.getMessage());
                }
            }
        }

        return null; // No matching publication found
    }
}
