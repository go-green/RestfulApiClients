package ApiServices.Contracts;

import ApiServices.Models.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.concurrent.CompletableFuture;

public interface IRestful {
    <T> CompletableFuture<Response<T>> getAsync(UriOptions endpoint, Headers headers, TypeReference<T> type);

    <T> CompletableFuture<Response<T>> putJsonAsync(UriOptions endpoint, Headers headers, RawPayLoad payload, TypeReference<T> type);

    <T> CompletableFuture<Response<T>> postFormAsync(UriOptions endpoint, Headers headers, FormData payload, TypeReference<T> type);

    <T> CompletableFuture<Response<T>> postJsonAsync(UriOptions endpoint, Headers headers, RawPayLoad payload, TypeReference<T> type);

    <T> CompletableFuture<Response<T>> deleteAsync(UriOptions endpoint, Headers headers);
}
