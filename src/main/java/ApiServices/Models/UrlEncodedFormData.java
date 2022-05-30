package ApiServices.Models;

import java.util.Map;

public class UrlEncodedFormData extends BasePayLoad {
    private Map<String, String> formData;

    public UrlEncodedFormData(BodyType type, Map<String, String> formData) {
        super(type);
        this.formData = formData;
    }

    public Map<String, String> getFormData() {
        return formData;
    }
}
