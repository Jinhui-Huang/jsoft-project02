package com.myhd.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.myhd.util.code.Code;
import com.myhd.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: TokenUtil
 * <br></br>
 * className: TokenUtil
 * <br></br>
 * packageName: com.jr.util
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/19 09:28
 */
@Slf4j
public class TokenUtil {
    public static final ThreadLocal<Object> SERVER_LOCAL = new ThreadLocal<>();

    /*token过期时间24小时*/
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000 * 2;
    /*密钥*/
    private static final String TOKEN_SECRET = "1yDHuds8fslZmwenHjh";

    private static final String ISS_USER = "Jinhui-Huang";

    /**
     * Description: sign 进行签名生成Token
     *
     * @return java.lang.String
     * @author jinhui-huang
     * @Date 2023/9/19
     */
    public static String sign(Object obj) {
        String token = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            JWTCreator.Builder builder = JWT.create();
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            int count = 0;
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                String getMethod = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Object invoke = clazz.getDeclaredMethod(getMethod).invoke(obj);
                if (invoke != null) {
                    switch (invoke.getClass().getSimpleName()) {
                        case "Boolean":
                            builder.withClaim(name, (Boolean) invoke);
                            break;
                        case "Integer":
                            builder.withClaim(name, (Integer) invoke);
                            break;
                        case "String":
                            builder.withClaim(name, (String) invoke);
                            break;
                        case "Long":
                            builder.withClaim(name, (Long) invoke);
                            break;
                        case "Double":
                            builder.withClaim(name, (Double) invoke);
                            break;
                        case "Date":
                            builder.withClaim(name, (Date) invoke);
                            break;
                        case "Long[]":
                            builder.withArrayClaim(name, (Long[]) invoke);
                            break;
                        case "Integer[]":
                            builder.withArrayClaim(name, (Integer[]) invoke);
                            break;
                        case "String[]":
                            builder.withArrayClaim(name, (String[]) invoke);
                            break;
                        default:
                            count++;
                            break;
                    }
                }
            }
            if (count == fields.length) {
                throw new BusinessException(Code.BUSINESS_ERR, "该对象不合法, 必须要有成员变量并且赋基本变量的包装类的值");
            }
            token = builder
                    /*发行人*/
                    .withIssuer(ISS_USER)
                    /*过期时间*/
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return token;
    }

    /**
     * Description: verify 对token进行验证并反会对应的token信息
     *
     * @param token 验证的token
     * @return java.lang.Boolean
     * @author jinhui-huang
     * @Date 2023/9/19
     */
    public static Map<String, Object> verify(String token, Class<?> clazz) {
        Map<String, Object> map = new HashMap<>();
        Object obj = null;
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(ISS_USER).build();
            jwtVerifier.verify(token);
            DecodedJWT decode = JWT.decode(token);
            /*实例对象*/
            obj = clazz.getConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                Claim claim = decode.getClaim(field.getName());
                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                if (!claim.isNull() && !claim.isMissing()) {
                    switch (field.getType().getSimpleName()) {
                        case "Boolean": {
                            Method method = clazz.getMethod(methodName, Boolean.class);
                            method.invoke(obj, decode.getClaim(name).asBoolean());
                            break;
                        }
                        case "Integer": {
                            Method method = clazz.getMethod(methodName, Integer.class);
                            method.invoke(obj, decode.getClaim(name).asInt());
                            break;
                        }
                        case "String": {
                            Method method = clazz.getMethod(methodName, String.class);
                            method.invoke(obj, decode.getClaim(name).asString());
                            break;
                        }
                        case "Long": {
                            Method method = clazz.getMethod(methodName, Long.class);
                            method.invoke(obj, decode.getClaim(name).asLong());
                            break;
                        }
                        case "Double": {
                            Method method = clazz.getMethod(methodName, Double.class);
                            method.invoke(obj, decode.getClaim(name).asDouble());
                            break;
                        }
                        case "Date": {
                            Method method = clazz.getMethod(methodName, Date.class);
                            method.invoke(obj, decode.getClaim(name).asDate());
                            break;
                        }
                        case "Long[]": {
                            Method method = clazz.getMethod(methodName, Long[].class);
                            method.invoke(obj, Arrays.stream(decode.getClaim(name).asArray(Long.class)).toArray());
                            break;
                        }
                        case "Integer[]": {
                            Method method = clazz.getMethod(methodName, Integer[].class);
                            method.invoke(obj, Arrays.stream(decode.getClaim(name).asArray(Integer.class)).toArray());
                            break;
                        }
                        case "String[]": {
                            Method method = clazz.getMethod(methodName, String.class);
                            method.invoke(obj, Arrays.stream(decode.getClaim(name).asArray(String.class)).toArray());
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
            map.put("status", true);
            map.put(clazz.getSimpleName(), obj);
            return map;
        } catch (SignatureVerificationException e) {
            log.error("[请求失败]:无效签名", e);
            map.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            log.error("[请求失败]:token过期", e);
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {
            log.error("[请求失败]:token算法不一致", e);
            map.put("msg", "token算法不一致");
        } catch (Exception e) {
            log.error("[请求失败]:请求失败", e);
            map.put("msg", "token无效");
        }
        map.put("status", false);
        map.put(clazz.getSimpleName(), obj);
        return map;
    }
}
