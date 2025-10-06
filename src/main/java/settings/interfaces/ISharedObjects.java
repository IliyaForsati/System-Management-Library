package settings.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ISharedObjects {
    ObjectMapper mapper = new ObjectMapper();
}
