package socket;

import java.util.concurrent.ExecutorService;

public class ThreadUtil {
    public static void execute(Runnable runnable) {
        GlobalThreadPool.execute(runnable);
    }
//    public static ExecutorService newExecutor(int corePoolSize) {
//
//    }
}
