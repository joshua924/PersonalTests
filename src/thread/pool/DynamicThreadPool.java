package thread.pool;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Log4j
public class DynamicThreadPool extends ThreadPoolExecutor {
    private boolean isPaused = false;
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            while (isPaused) {
                unpaused.await();
            }
        } catch (InterruptedException e) {
            t.interrupt();
        } finally {
            pauseLock.unlock();
        }
    }

    public DynamicThreadPool(int initialThreadCount, BlockingQueue<Runnable> queue, ThreadFactory threadFactory) {
        super(initialThreadCount, initialThreadCount, 0, TimeUnit.SECONDS, queue, threadFactory);
        setRejectedExecutionHandler(new ZeroSizeRejectedExecutionHandler());
    }

    public void resize(int size) {
        if (size == 0) {
            pause();
            setCorePoolSize(0);
            setMaximumPoolSize(1);
        } else {
            resume();
            setCorePoolSize(size);
            setMaximumPoolSize(size);
            prestartAllCoreThreads();
        }
    }

    private void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
        } finally {
            pauseLock.unlock();
        }
    }

    private void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }

    private class ZeroSizeRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.info("Task " + r.toString() + " is rejected as the size is 0");
        }
    }
}
