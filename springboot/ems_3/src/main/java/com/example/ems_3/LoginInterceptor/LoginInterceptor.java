package com.example.ems_3.LoginInterceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ems_3.service.ex.MyException;
import com.example.ems_3.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义处理器拦截器
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
/*
    @Autowired
    private UserMapper userMapper;
*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println(token);
        if (StringUtils.isEmpty(token)) {
            throw new MyException("token不能为空");
        }
/*

        String username=JWTUtils.getUserInfo(token);
        if(username==null) {
            throw new UserNotFoundException("token对应用户不存在");
        }
        User user=userMapper.findByUsername(username);
        if(user==null) {
            throw new UserNotFoundException("token对应用户不存在");
        }
*/
        try {
            //验证token
            DecodedJWT decodedJWT = JWTUtils.verify(token/*,user*/);
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            log.error("无效签名!", e);
            return false;
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            log.error("token过期!", e);
            return false;
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            log.error("token算法不一致!", e);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("token无效!", e);
            return false;
        }
    }
}
