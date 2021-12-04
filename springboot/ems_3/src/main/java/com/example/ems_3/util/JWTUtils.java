package com.example.ems_3.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ems_3.entity.User;

import java.util.Calendar;

public class JWTUtils {

    private static final String SECRET = "!Q@W#E$R^Y&U";
/*
//    //token签发者
//    private static final String ISSUSRE = "HZSTYGZPT";

    //token过期时间
    public static final Long EXPIRE_DATE = 1000 * 60L;

*/

    /*获取token*/
    public static String getToken(User u) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create().withClaim("userId", u.getId())
                .withClaim("username", u.getUsername());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(/*u.getPassword())*/SECRET));
    }

    /**
     * 验证token
     * 验证过程中如果有异常，则抛出；
     */
    public static DecodedJWT verify(String token/*,User user*/) {
/*
        String username=user.getUsername();
        String password= user.getPassword();
        Integer userId=user.getId();
*/
        return JWT.require(Algorithm.HMAC256(/*password*/SECRET))/*.withClaim("username",username).withClaim("userId",userId)*/.build().verify(token);
    }

    /*获取token信息*/
    public static Integer getUserInfo(String token) {
        //通过token获得保存在里面的用户的id
        DecodedJWT decodedJWT = verify(token);
        Integer userId =decodedJWT.getClaim("userId").asInt();
/*
        System.out.println(decodedJWT.getClaim("username").asString());
        System.out.println(userId);
*/
        return userId;
    }


}
