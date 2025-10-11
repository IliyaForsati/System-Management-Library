package model.enums;

import model.*;
import services.ArticleService;
import services.BookService;
import services.DissertationService;
import services.MagazineService;
import services.interfaces.*;

public enum Type {
    BOOK(Book.class, IBookService.class),
    MAGAZINE(Magazine.class, IMagazineService.class),
    ARTICLE(Article.class, IArticleService.class),
    DISSERTATION(Dissertation.class, IDissertationService.class);

    final Class<?> model;
    final Class<?> service;
    Type(Class<?> model, Class<?> service) {
        this.model = model;
        this.service = service;
    }

    @SuppressWarnings("unchecked")
    public <T extends Publication> Class<T> getModel() {
        return (Class<T>) model;
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
