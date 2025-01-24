package org.test.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import net.javacrumbs.shedlock.cdi.SchedulerLock;

import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class TestScheduler {

    private AtomicInteger counter = new AtomicInteger();

    @Scheduled(every = "15s")
    @SchedulerLock(name = "increment", lockAtLeastFor = "PT60S", lockAtMostFor = "PT60S")
    void increment() {
        counter.incrementAndGet();
    }

    @Scheduled(every = "10s")
    @SchedulerLock(name = "increment", lockAtLeastFor = "PT60S", lockAtMostFor = "PT60S")
    void incrementV2() {
        counter.incrementAndGet();
    }

}
