package socket;

import core.thread.GlobalThreadPool;

public class ThreadUtil {
    public static void execute(Runnable runnable) {
        GlobalThreadPool.execute(runnable);
    }
//    public static ExecutorService newExecutor(int corePoolSize) {
//
//    }
}
