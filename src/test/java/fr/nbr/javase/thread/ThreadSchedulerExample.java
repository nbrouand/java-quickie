package fr.nbr.javase.thread;

import static com.jayway.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * Test scheduler
 */
public class ThreadSchedulerExample {

    private long time = 1;

    @Test
    public void testScheduler() {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(() -> time += 2, 1, 2, TimeUnit.SECONDS);
        await().until(() -> assertThat(time).isGreaterThan(8));
    }

}
