package settings.serviceProvider;

import java.util.function.Supplier;

public class ServiceDescriptor<T> {
    public final Class<T> serviceType;
    public final Supplier<T> factory;
    public final ServiceLifetime lifetime;
    public T singletonInstance;

    public ServiceDescriptor(Class<T> serviceType, Supplier<T> factory, ServiceLifetime lifetime) {
        this.serviceType = serviceType;
        this.factory = factory;
        this.lifetime = lifetime;
    }
}
