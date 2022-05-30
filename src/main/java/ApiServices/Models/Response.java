package ApiServices.Models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.net.URI;
import java.util.Map;

public class Response<T> {
    private T content;
    private int statusCode;
    private boolean isSuccessful;
    private String statusDescription;
    private Exception exception;
    private Map<String, String> headers;
    private URI uri;

    public Response(int statusCode, Map<String, String> headers, T content, boolean isSuccessful, URI uri) {
        this.content = content;
        this.statusCode = statusCode;
        this.headers = headers;
        this.isSuccessful = isSuccessful;
        this.uri = uri;
    }

    @JsonCreator
    public Response(T content) {
        this.content = content;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccessful() {
        return this.isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public URI getUri() {
        return this.uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
