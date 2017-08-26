package thread.pool;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.*;

@Log4j
public class DynamicThreadPool extends ThreadPoolExecutor {
    private Semaphore canExecute = new Semaphore(1);

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        try {
            canExecute.acquire();
            canExecute.release();
        } catch (InterruptedException e) {
            t.interrupt();
        }
    }

    public DynamicThreadPool(int initialThreadCount, BlockingQueue<Runnable> queue, ThreadFactory threadFactory) {
        super(initialThreadCount, initialThreadCount, 0, TimeUnit.SECONDS, queue, threadFactory);
        setRejectedExecutionHandler(new ZeroSizeRejectedExecutionHandler());
    }

    public void resize(int size) {
        try {
            if (size == 0) {
                canExecute.acquire();
                setCorePoolSize(0);
                setMaximumPoolSize(1);
            } else {
                canExecute.release();
                setCorePoolSize(size);
                setMaximumPoolSize(size);
                prestartAllCoreThreads();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to resize", e);
        }
    }

    private class ZeroSizeRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.info("Task " + r.toString() + " is rejected as the size is 0");
        }
    }
}
