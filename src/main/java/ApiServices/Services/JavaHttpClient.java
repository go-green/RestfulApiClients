package ApiServices.Services;

import ApiServices.Contracts.IRestful;
import ApiServices.Models.*;
import Configuration.Settings;
import Utils.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mizosoft.methanol.MediaType;
import com.github.mizosoft.methanol.Methanol;
import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.github.mizosoft.methanol.MutableRequest;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.FileNotFoundException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;

@Singleton
public class JavaHttpClient implements IRestful {
    private HttpClient httpClient;

    private Settings settings;

    @Inject
    public JavaHttpClient(Settings settings) {
        this.settings = settings;
        var duration = Duration.of(Long.parseLong(this.settings.get("api.connectTimeout")), ChronoUnit.SECONDS);
        this.httpClient = Methanol.newBuilder().connectTimeout(duration).build();
    }

    @Override
    public <T> CompletableFuture<Response<T>> getAsync(UriOptions endpoint, Headers headers, TypeReference<T> type) {
        var builder = HttpRequest.newBuilder()
                .uri(endpoint.getAbsoluteUri())
                .GET();
        for (var entry : headers.getMessageHeaders().entrySet()) {
            builder.setHeader(entry.getKey(), entry.getValue());
        }
        var request = builder.build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(r -> getResponseCompletableFuture(r, type));
    }

    @Override
    public <T> CompletableFuture<Response<T>> putJsonAsync(UriOptions endpoint, Headers headers, RawPayLoad payload, TypeReference<T> type) {
        var requestBuilder = HttpRequest.newBuilder()
                .uri(endpoint.getAbsoluteUri())
                .PUT(HttpRequest.BodyPublishers.ofString(payload.getPayLoad()));
        addHeaders(headers, requestBuilder);
        var request = requestBuilder.build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(r -> getResponseCompletableFuture(r, type));
    }


    @Override
    public <T> CompletableFuture<Response<T>> postFormAsync(UriOptions endpoint, Headers headers, FormData payload, TypeReference<T> type) {
        var bodyPublisher = MultipartBodyPublisher.newBuilder();
        addFormData(payload, bodyPublisher);
        var requestBuilder = MutableRequest.POST(endpoint.getAbsoluteUri(), bodyPublisher.build());
        addHeaders(headers, requestBuilder);
        var request = requestBuilder.build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(r -> getResponseCompletableFuture(r, type));
    }

    @Override
    public <T> CompletableFuture<Response<T>> postJsonAsync(UriOptions endpoint, Headers headers, RawPayLoad payload, TypeReference<T> type) {
        var requestBuilder = HttpRequest.newBuilder()
                .uri(endpoint.getAbsoluteUri())
                .POST(HttpRequest.BodyPublishers.ofString(payload.getPayLoad()));
        addHeaders(headers, requestBuilder);
        var request = requestBuilder.build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(r -> getResponseCompletableFuture(r, type));
    }


    public <T> CompletableFuture<Response<T>> deleteAsync(UriOptions endpoint, Headers headers) {
        var requestBuilder = HttpRequest.newBuilder()
                .uri(endpoint.getAbsoluteUri())
                .DELETE();
        var request = requestBuilder.build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(r -> getResponseCompletableFuture(r, new TypeReference<T>() {
                }));
    }

    private <T> void addFormData(T payload, MultipartBodyPublisher.Builder bodyPublisher) {

        if (payload.getClass() == FormData.class && ((FormData) payload).getFilesInfo() != null) {
            for (var entry : ((FormData) payload).getFilesInfo().entrySet()) {
                try {
                    bodyPublisher.filePart(entry.getKey(), entry.getValue().getPath(), MediaType.parse(entry.getValue().getMediaType()));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (payload.getClass() == FormData.class && ((FormData) payload).getFormData() != null) {
            for (var entry : ((FormData) payload).getFormData().entrySet()) {
                bodyPublisher.textPart(entry.getKey(), entry.getValue());
            }
        }
    }

    private void addHeaders(Headers headers, HttpRequest.Builder builder) {

        if (!headers.getMessageHeaders().isEmpty()) {
            for (var entry : headers.getMessageHeaders().entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
    }

    private <T> Response<T> getResponseCompletableFuture(HttpResponse<String> r, TypeReference<T> type) {
        var objectMapper = new ObjectMapper();
        try {
            var value = objectMapper.readValue(r.body(), type);
            return new Response<T>(
                    r.statusCode(),
                    HttpUtils.headersToMap(r.headers().map()),
                    value,
                    HttpUtils.isSuccess(r.statusCode()),
                    r.uri()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
