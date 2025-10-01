package settings.serviceProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ServiceProvider {
    private final Map<Class<?>, ServiceDescriptor<?>> services = new HashMap<>();
    private final Map<Class<?>, Object> scopedInstances = new HashMap<>();
    public final static ServiceProvider mainScope = new ServiceProvider();

    public <T> void addSingleton(Class<T> type, Supplier<T> factory) {
        services.put(type, new ServiceDescriptor<>(type, factory, ServiceLifetime.SINGLETON));
    }

    public <T> void addScoped(Class<T> type, Supplier<T> factory) {
        services.put(type, new ServiceDescriptor<>(type, factory, ServiceLifetime.SCOPED));
    }

    public  <T> void addTransient(Class<T> type, Supplier<T> factory) {
        services.put(type, new ServiceDescriptor<>(type, factory, ServiceLifetime.TRANSIENT));
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> type) {
        ServiceDescriptor<T> descriptor = (ServiceDescriptor<T>) services.get(type);
        if (descriptor == null) {
            throw new RuntimeException("Service not registered: " + type);
        }

        switch (descriptor.lifetime) {
            case SINGLETON:
                if (descriptor.singletonInstance == null) {
                    descriptor.singletonInstance = descriptor.factory.get();
                }
                return descriptor.singletonInstance;

            case SCOPED:
                if (!scopedInstances.containsKey(type)) {
                    scopedInstances.put(type, descriptor.factory.get());
                }
                return (T) scopedInstances.get(type);

            case TRANSIENT:
                return descriptor.factory.get();

            default:
                throw new IllegalStateException("Unknown lifetime");
        }
    }

    public ServiceProvider createScope() {
        ServiceProvider scopedProvider = new ServiceProvider();
        scopedProvider.services.putAll(this.services); // کپی descriptor ها
        return scopedProvider;
    }
}
