package com.myhd.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: ReqRespMsgUtil
 * <br></br>
 * className: ReqRespMsgUtil
 * <br></br>
 * packageName: com.myhd.util
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/15 10:58
 */
public class ReqRespMsgUtil {
    public static final Map<String, HttpServletResponse> requestObj = new HashMap<>();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void sendMsg(HttpServletResponse resp, Object obj) {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter respWriter = resp.getWriter();
            String json = objectMapper.writeValueAsString(obj);
            respWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: getMsg 仅限于json数据的获取, 没有获取到对象会返回null
     * @return void
     * @author jinhui-huang
     * @Date 2023/9/18
     * */
    public static <T> T getMsg(HttpServletRequest req, Class<T> clazz) {
        try {
            return objectMapper.readValue(req.getInputStream(), clazz);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
