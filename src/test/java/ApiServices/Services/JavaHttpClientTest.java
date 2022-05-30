package ApiServices.Services;

import ApiServices.Contracts.IRestful;
import ApiServices.Dtos.Student;
import ApiServices.Models.Headers;
import ApiServices.Models.UriOptions;
import Configuration.ApiDependenciesModule;
import Configuration.Settings;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.inject.Guice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

class JavaHttpClientTest {

    @BeforeEach
    public void SetUp() {
        var injector = Guice.createInjector(new ApiDependenciesModule());
        restApiClient = injector.getInstance(IRestful.class);
        settings = injector.getInstance(Settings.class);
    }

    private IRestful restApiClient;
    private Settings settings;

    @Test
    void getAsync() throws ExecutionException, InterruptedException {
        // Arrange
        var baseUrl = settings.get("api.baseUri");
        var options = new UriOptions(baseUrl, "students");
        var headers = new Headers(new HashMap<String, String>(), new HashMap<String, String>());

        // Act
        var r = restApiClient.<List<Student>>getAsync(options, headers, new TypeReference<List<Student>>() {
        }).get();

        // Assert
        Assertions.assertEquals(200, r.getStatusCode());
        var student = r.getContent().get(0);
        Assertions.assertEquals("Jhon", student.firstname);
        Assertions.assertEquals("McDonalds", student.lastname);
        Assertions.assertTrue(r.isSuccessful());
        Assertions.assertTrue(!r.getHeaders().isEmpty());
    }

    @Test
    void putJsonAsync() {
    }

    @Test
    void postFormAsync() {
    }

    @Test
    void postJsonAsync() {
    }

    @Test
    void deleteAsync() {
    }
}