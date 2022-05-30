package ApiServices.Models;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private Map<String, String> messageHeaders;

    public Headers(Map<String, String> messageHeaders, Map<String, String> requestHeaders) {
        this.messageHeaders = messageHeaders;
    }

    public Headers() {
        this.messageHeaders = new HashMap<>();
    }

    public void setMessageHeader(String key, String value) {
        this.messageHeaders.put(key, value);
    }


    public void setMessageHeaders(Map<String, String> messageHeaders) {
        this.messageHeaders = messageHeaders;
    }


    public Map<String, String> getMessageHeaders() {
        return messageHeaders;
    }
    
}
