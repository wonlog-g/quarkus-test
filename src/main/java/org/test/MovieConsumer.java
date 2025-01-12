package org.test;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MovieConsumer {

    private final Logger logger = Logger.getLogger(MovieConsumer.class);

    @Incoming("movies-in")
    public void receive(Record<Integer, String> record) {
        messageOccurredException();
        logger.infof("Got a movie: %d - %s", record.key(), record.value());
    }

    public void messageOccurredException() {
        logger.infof("MovieConsumer EXCEPTION");
        throw new RuntimeException();
    }
}
