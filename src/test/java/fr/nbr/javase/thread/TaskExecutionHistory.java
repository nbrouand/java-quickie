package fr.nbr.javase.thread;

import static com.jayway.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * History of async task execution
 */
public class TaskExecutionHistory {

    @Test
    public void test() {
        final List<String> list = new ArrayList<>();
        java1(list);
        java5(list);
        java8_lambda(list);
        await().until(() -> assertThat(list).hasSize(3));
    }

    /**
     * 1997
     */
    private void java1(List<String> mock) {
        new Thread(new Runnable() {
            public void run() {
                someLongProcess(mock);
            }
        }).start();
    }

    /**
     * 2002
     */
    private void java5(List<String> mock) {
        final ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new Runnable() {
            public void run() {
                someLongProcess(mock);
            }
        });
    }

    /**
     * 2005
     */
    private void java8_lambda(List<String> mock) {
        final ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(() -> someLongProcess(mock));
    }

    private void someLongProcess(List<String> mock) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            mock.add("test");
        } catch (InterruptedException e) {
        }
    }
}
