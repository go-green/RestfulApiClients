package ApiServices.Models;

import java.nio.file.Path;
import java.util.Map;

public class Payload {
    private BodyType type;
    private Object payload;
    private Path path;
    private Map<String, String> formData;

    public Payload(BodyType type, Object payload) {
        this.type = type;
        this.payload = payload;
    }

    public Payload(BodyType type, String path) {
        this.type = type;
        this.path = Path.of(path);
    }

    public Payload(BodyType type, Map<String, String> formData) {
        this.type = type;
        this.formData = formData;
    }

    public BodyType getType() {
        return this.type;
    }

    public Object getPayload() {
        return this.payload;
    }

    public Path getPath() {
        return this.path;
    }

    public Map<String, String> getFormData() {
        return this.formData;
    }
}
