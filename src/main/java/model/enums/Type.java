package model.enums;

import model.*;
import services.ArticleService;
import services.BookService;
import services.DissertationService;
import services.MagazineService;
import services.interfaces.IPublicationService;

public enum Type {
    BOOK(Book.class, BookService.class),
    MAGAZINE(Magazine.class, MagazineService.class),
    ARTICLE(Article.class, ArticleService.class),
    DISSERTATION(Dissertation.class, DissertationService.class);

    final Class<?> model;
    final Class<?> service;
    Type(Class<?> model, Class<?> service) {
        this.model = model;
        this.service = service;
    }

    public Class<?> getService() {
        return service;
    }
    @SuppressWarnings("unchecked")
    public static <T extends Publication, P extends IPublicationService<T>> Class<P> getServiceByModelClass(Class<T> clazz) {
        for (Type t : Type.values()) {
            if (t.model.equals(clazz))
                return (Class<P>) t.service;
        }
        return null;
    }
}
