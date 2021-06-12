package socket;

public class RuntimeUtil {
    /**
     * Get the number of available processors
     *
     */
    public static int getProcessorCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }
}
