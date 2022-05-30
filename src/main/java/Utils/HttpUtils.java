package Utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    public static boolean isSuccess(int statusCode)
    {
        return (statusCode >= 200) && (statusCode <= 299);
    }

    public static Map<String,String> headersToMap(Map<String, List<String>> headers)
    {
        var map = new HashMap<String,String>();
        for (var entry:headers.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get(0));
        }
        return map;
    }
}
