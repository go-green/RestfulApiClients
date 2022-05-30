package ApiServices.Models;

public class RawPayLoad extends BasePayLoad {
    private String payLoad;

    private String mediaType;

    public RawPayLoad(BodyType type, String payLoad, String mediaType) {
        super(type);
        this.payLoad = payLoad;
        this.mediaType = mediaType;
    }

    public String getPayLoad() {
        return payLoad;
    }

    public String getMediaType() {
        return mediaType;
    }
}
