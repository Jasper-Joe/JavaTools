package core.io;

public interface StreamProgress {
    void start();

    void progress(long progressSize);

    void finish();
}
