package ApiServices.Models;

import java.util.Map;

public class FormData extends BasePayLoad {
    private Map<String, Object> formData;
    private Map<String, FileInfo> filesInfo;

    public FormData(BodyType type, Map<String, Object> formData, Map<String, FileInfo> filesInfo) {
        super(type);
        this.formData = formData;
        this.filesInfo = filesInfo;
    }

    public Map<String, Object> getFormData() {
        return formData;
    }

    public Map<String, FileInfo> getFilesInfo() {
        return filesInfo;
    }
}
