package ApiServices.Models;

import java.nio.file.Path;

public class FileInfo {
    private Path path;
    private String mediaType;

    public FileInfo(Path path, String mediaType) {
        this.path = path;
        this.mediaType = mediaType;
    }

    public Path getPath() {
        return path;
    }

    public String getMediaType() {
        return mediaType;
    }
}
