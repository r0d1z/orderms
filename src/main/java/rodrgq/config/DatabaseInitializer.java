package rodrgq.config;

import com.mongodb.client.model.Indexes;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import rodrgq.entity.OrderEntity;

@ApplicationScoped
public class DatabaseInitializer {
    void OnStart(@Observes StartupEvent event) {
        OrderEntity.mongoCollection().createIndex(Indexes.ascending("customerId"));
    }
}
