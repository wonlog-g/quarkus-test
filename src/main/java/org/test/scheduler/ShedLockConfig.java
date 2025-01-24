package org.test.scheduler;

import com.mongodb.client.MongoClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ShedLockConfig {

    @ConfigProperty(name = "quarkus.mongodb.database")
    String database;

    @Singleton
    public LockProvider lockProvider(MongoClient mongoClient) {
        return new MongoLockProvider(mongoClient.getDatabase(database));
    }
}
