package thread.pool;

import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
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
        assertEquals(0, count.get());
        assertTrue(dynamicThreadPool.getQueue().size() > 0);
        dynamicThreadPool.resize(5);
        while (count.get() != 1000) ;
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
}
