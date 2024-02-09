package proj.utils;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {
    private static Cloudinary cloudinary;

    public static Cloudinary getInstance(){
        if (cloudinary == null){
            Map<String,String> configMap = new HashMap<>();
            configMap.put("cloud_name","djr7hqzh7");
            configMap.put("api_key","662686827414461");
            configMap.put("api_secret","OJambOoMgPmhPjokER-QsHh5PIE");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
