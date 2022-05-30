package ApiServices.Models;

import java.nio.file.Path;

public class BinaryPayLoad extends BasePayLoad {
    private Path path;

    public BinaryPayLoad(BodyType type, Path path) {
        super(type);
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }
}
