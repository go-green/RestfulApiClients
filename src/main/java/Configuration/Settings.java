package Configuration;

import com.google.inject.Singleton;

import java.io.FileReader;
import java.util.Properties;

@Singleton
public class Settings {
    private Properties properties;

    public Settings() {
        var path = getClass().getClassLoader().getResource("services.properties").getPath();
        try {
            var reader = new FileReader(path);
            this.properties = new Properties();
            this.properties.load(reader);
            reader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String propName) {
        return properties.getProperty(propName);
    }
}
