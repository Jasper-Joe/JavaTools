package http;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpInputStream extends InputStream{
    private InputStream in;

    @Override
    public int read() throws IOException {
        return this.in.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.in.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    @Override
    public long skip(long n) throws IOException {
        return this.in.skip(n);
    }

    @Override
    public int available() throws IOException {
        return this.in.available();
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        this.in.reset();
    }

    @Override
    public boolean markSupported() {
        return this.in.markSupported();
    }

//    private void init(HttpResponse response) {
//        try {
//            this.in = (response.status < HttpStatus.HTTP_BAD_REQUEST) ? response.h
//        } catch (IOException e) {
//            if (!(e instanceof FileNotFoundException)) {
//                throw new HttpException(e);
//            }
//            // no content to return
//        }
//
//        if (this.in == null) {
//            this.in = new ByteArrayInputStream()
//        }
//    }
}
