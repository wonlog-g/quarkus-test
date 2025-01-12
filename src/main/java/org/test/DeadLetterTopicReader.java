package org.test;

import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class DeadLetterTopicReader {

    static final Logger LOGGER = Logger.getLogger("Kafka-Dead-Letter-Topic");

    @Incoming("dead-letter-topic-movies")
    public CompletionStage<Void> dead(Message<String> rejected) {
        IncomingKafkaRecordMetadata<String, String> metadata = rejected.getMetadata(IncomingKafkaRecordMetadata.class)
                .orElseThrow(() -> new IllegalArgumentException("Expected a message coming from kafka"));
        String reason = new String(metadata.getHeaders().lastHeader("dead-letter-reason").value());

        LOGGER.infof("The message '%s' has been rejected and sent to the DLT. The reason is: '%s'.", rejected.getPayload(), reason);

        return rejected.ack();
    }
}
