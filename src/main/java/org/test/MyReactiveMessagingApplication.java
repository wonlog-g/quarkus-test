package org.test;

import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import java.util.TimeZone;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    void onStart(@Observes StartupEvent ev) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Log.info("[CUSTOM-LOGGING] The application is starting..." + ev.toString());
    }

    void onShutdown(@Observes ShutdownEvent ev) {
        Log.info("[CUSTOM-LOGGING] The application is stopping..." + ev.toString());
    }
}
