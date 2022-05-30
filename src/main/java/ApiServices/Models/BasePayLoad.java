package ApiServices.Models;

public class BasePayLoad {
    private BodyType type;

    public BasePayLoad(BodyType type) {
        this.type = type;
    }

    public BodyType getType() {
        return type;
    }
}
