package http;

import java.net.http.HttpRequest;
import java.util.Locale;

public class HttpUtil {

    public static boolean isHttps(String url) {
        return url.toLowerCase().startsWith("https:");
    }

    public static boolean isHttp(String url) {
        return url.toLowerCase().startsWith("http:");
    }

    public static HttpRequest createPost(String url) {
        return HttpRequest.post(url);
    }

    public static http.HttpRequest createRequest(Method method, String url) {
        return new http.HttpRequest(url).method(method);
    }
}
