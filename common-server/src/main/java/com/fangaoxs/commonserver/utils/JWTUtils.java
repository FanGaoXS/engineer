package com.fangaoxs.commonserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/24/16:44
 * @Description: JWT工具类（当前时间戳根据payload、密钥、算法、有效时间生成的唯一token）：
 *                      1、生成token
 *                      2、解开token
 */

public class JWTUtils {

    /*
    *   secret密钥
    * */
    private static final String SECRET = "fangaoxs";

    /**
     *  生成JWT对象（重载，可以指定有效期（单位天））
     * @param payloadMap    有效负载集合
     * @param validityDay   有效时间
     * @return token    JWT令牌
     */
    public static String createToken(Map<String,String> payloadMap,Integer validityDay){
        if (validityDay == null){
            validityDay = 7; //如果用户没有自定义有效期，则指定有效期为7
        }
        //1、创建JWT的builder构造器
        JWTCreator.Builder jwtBuilder = JWT.create();
        //2、遍历payloadMap然后利用withClaim添加到JWT中
        payloadMap.forEach((key,value)->{ //lambda表达式遍历map
            jwtBuilder.withClaim(key,value);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,validityDay);//单位天
        String token = jwtBuilder
                .withExpiresAt(instance.getTime())  //3、添加JWT失效时间
                .sign(Algorithm.HMAC256(SECRET));   //4、利用算法结合SECRET给JWT签名
        return token;
    }

    /**
     *  获取成功解密后的token对象（然后获取其中的payload）
     * @param token JWT令牌
     * @return decodedJWT   解密后的JWT对象
     */
    public static DecodedJWT getTokenInfo(String token){
        try {
            //1、结合算法生成准备验证token的验证器
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            //2、解密JWT对象
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (SignatureVerificationException e) { //签名不一致
            throw new RuntimeException("签名不一致");
        } catch (TokenExpiredException e) {  //token已过期
            throw new RuntimeException("token已过期");
        } catch (AlgorithmMismatchException e) { //算法不匹配
            throw new RuntimeException("算法不匹配");
        } catch (InvalidClaimException e) { // payload已失效
            throw new RuntimeException("payload已失效");
        }
    }

}
