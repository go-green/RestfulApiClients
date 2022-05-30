package Configuration;

import ApiServices.Contracts.IRestful;
import ApiServices.Services.JavaHttpClient;
import com.google.inject.AbstractModule;

public class ApiDependenciesModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IRestful.class).to(JavaHttpClient.class);
        bind(Settings.class).toInstance(new Settings());
    }
}
