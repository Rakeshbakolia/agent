package com.hypertest.agent;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.http.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RequestInterceptor {

    @RuntimeType
    public static ResponseEntity<?> intercept(@Origin Method method, @AllArguments Object[] arguments) throws IllegalAccessException {
        System.out.println("======In The Method =======");
        System.out.println(method.getName());
        Object postName = null;
        Object postContent = null;
        for(Object obj: arguments){
            Field[] fields = obj.getClass().getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                if("postName".equals(field.getName())){
                    postName = field.get(obj);
                }
                if("postContent".equals(field.getName())){
                    postContent = field.get(obj);
                }
            }
        }
        ResponseEntity<?> response = null;
        try {
            Object body = getResponseData(postName, postContent);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response = new ResponseEntity<>(body, headers, HttpStatus.CREATED);
        } catch (Exception e){
            e.fillInStackTrace();
        }
        return response;
    }

    private static Map<Object, Object> getResponseData(Object postName, Object postContent){
        Map<String, Object> httpOutBound = new HashMap<>();
        httpOutBound.put("abbreviation", "IST");
        httpOutBound.put("client_ip", "49.36.232.50");
        httpOutBound.put("datetime", "2024-03-25T02:15:37.971968+05:30");
        httpOutBound.put("day_of_week", 1);
        httpOutBound.put("day_of_year", 85);
        httpOutBound.put("dst", false);
        httpOutBound.put("dst_from", null);
        httpOutBound.put("dst_offset", 0);
        httpOutBound.put("dst_until", null);
        httpOutBound.put("raw_offset", 19800);
        httpOutBound.put("timezone", "Asia/Kolkata");
        httpOutBound.put("unixtime", 1711313137);
        httpOutBound.put("utc_datetime", "2024-03-24T20:45:37.971968+00:00");
        httpOutBound.put("utc_offset", "+05:30");
        httpOutBound.put("week_number", 13);
        return Map.of("db_post", Map.of("id", new Random().nextInt(1000),"post_name",postName, "post_content", postContent),
                "http_outbound", httpOutBound);
    }
}
