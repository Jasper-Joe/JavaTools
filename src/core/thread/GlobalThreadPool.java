package socket;

import java.util.concurrent.ExecutorService;

public class GlobalThreadPool {
    private static ExecutorService executor;
    private GlobalThreadPool() {

    }

    public static void execute(Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {

        }
    }

    public static ExecutorService getExecutor() {
        return executor;
    }
}
