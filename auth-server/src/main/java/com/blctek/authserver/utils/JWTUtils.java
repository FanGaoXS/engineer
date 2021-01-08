package com.blctek.authserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/24/16:44
 * @Description: JWT工具类：
 *                      1、下发token
 *                      2、验证token
 */

public class JWTUtils {

    /*
    *   secret密钥
    * */
    private static final String SECRET = "auth-server-d1";

    /**
     *  生成JWT对象
     * @param payloadMap    有效负载集合
     * @return token    JWT令牌
     */
    public static String createToken(Map<String,String> payloadMap){
        //1、创建JWT的builder构造器
        JWTCreator.Builder jwtBuilder = JWT.create();
        /*Set<String> keySet = payloadMap.keySet();
        for (String key : keySet) {
            System.out.println(key+"-->"+payloadMap.get(key));
        }*/
        //2、遍历payloadMap然后利用withClaim添加到JWT中
        payloadMap.forEach((key,value)->{
            //System.out.println(key+"-->"+value);
            jwtBuilder.withClaim(key,value);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7); // 7天token有效期
        String token = jwtBuilder
                .withExpiresAt(instance.getTime())  //3、添加JWT失效时间
                .sign(Algorithm.HMAC256(SECRET));   //4、利用算法结合SECRET给JWT签名
        return token;
    }

    /**
     *  获得验证签名后的JWT对象：
     * @param token JWT令牌
     * @return decodedJWT   解密后的JWT对象
     */
    public static DecodedJWT getTokenInfo(String token){
        //1、结合算法验证JWT的签名
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        //2、解密JWT对象
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    /*public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId","11");
        map.put("userName","wuqingke");
        String token = JWTUtils.createToken(map);
        System.out.println("生成的JWT->"+token);
        DecodedJWT decodedJWT = JWTUtils.getTokenInfo(token);
        System.out.println(decodedJWT.getClaim("userId").asString());
        System.out.println(decodedJWT.getClaim("userName").asString());
    }*/

}
