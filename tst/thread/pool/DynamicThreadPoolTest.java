package thread.pool;

import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DynamicThreadPoolTest {
    private DynamicThreadPool dynamicThreadPool;

    @Before
    public void setup() throws Exception {
        dynamicThreadPool = new DynamicThreadPool(10, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
    }

    @After
    public void teardown() throws Exception {
        dynamicThreadPool.shutdownNow();
        dynamicThreadPool.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    public void decreaseToZero() throws Exception {
        dynamicThreadPool.resize(0);
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            dynamicThreadPool.execute(new AddToCount(count));
        }
        // wait a while and verify that no tasks have run
        Thread.sleep(100);
        assertEquals(0, count.get());
    }

    @Test
    public void decreaseToZeroThenIncrease() throws Exception {
        Semaphore semaphore = new Semaphore(100);
        semaphore.acquire(100);
        dynamicThreadPool.resize(0);
        for (int i = 0; i < 100; i++) {
            dynamicThreadPool.execute(new ReleaseLock(semaphore));
        }
        // increase the size and verify that all pending tasks do run
        dynamicThreadPool.resize(10);
        semaphore.acquire(100);
    }

    @Test
    public void decrease() throws Exception {
        dynamicThreadPool.resize(1);
        Set<String> track = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            dynamicThreadPool.execute(new AddThreadNameToSet(track));
        }
        assertEquals(1, track.size());
    }

    @Test
    public void testRelease() throws Exception {
        dynamicThreadPool.resize(3);
        dynamicThreadPool.resize(3);
        dynamicThreadPool.resize(3);
    }

    @Test
    public void testAcquire() throws Exception {
        dynamicThreadPool.resize(0);
        dynamicThreadPool.resize(0);
        dynamicThreadPool.resize(0);
    }

    @AllArgsConstructor
    private static class AddThreadNameToSet implements Runnable {
        private Set<String> track;

        @Override
        public void run() {
            track.add(Thread.currentThread().getName());
        }
    }

    @AllArgsConstructor
    private static class AddToCount implements Runnable {
        private AtomicInteger count;

        @Override
        public void run() {
            count.incrementAndGet();
        }
    }

    @AllArgsConstructor
    private static class ReleaseLock implements Runnable {
        private Semaphore semaphore;

        @Override
        public void run() {
            semaphore.release();
        }
    }
}
