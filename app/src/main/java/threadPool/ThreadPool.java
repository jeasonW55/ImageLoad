package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    public static void getSingleThreadPool(Runnable task) {
        ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
        if (task == null) {
            return;
        }
        threadPoolExecutor.execute(task);
    }

}
