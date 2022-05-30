package ApiServices.Models;

import java.net.URI;
import java.util.Map;

public class UriOptions {
    private final String uri;
    private final String endpoint;
    private Map<String, String> params;

    public UriOptions(String uri, String endpoint, Map<String, String> params) {
        this.uri = uri;
        this.endpoint = endpoint;
        this.params = params;
    }

    public UriOptions(String uri, String endpoint) {
        this.uri = uri;
        this.endpoint = endpoint;
    }

    public URI getAbsoluteUri() {
        var builder = new StringBuilder(this.uri);
        if (this.endpoint != null) {
            builder.append("/");
            builder.append(endpoint);
        }
        if (this.params != null && !this.params.isEmpty()) {
            builder.append("?");
            for (var entry : this.params.entrySet()) {
                builder.append(entry.getKey() + "=" + entry.getValue());
            }
        }
        return URI.create(builder.toString());
    }
}
