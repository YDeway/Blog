package com.deway.blog.interceptor;

import com.deway.blog.config.AuthTokenConfig;
import com.deway.blog.entiry.auth.AccessToken;
import com.deway.blog.tool.HttpStatus;
import com.deway.blog.tool.JwtUtil;
import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

/**
 * 登录验证
 *
 * @author Deway
 */
@Component
@AllArgsConstructor
public class AuthInterceptor implements Interceptor {

    private final Jedis redis;
    private final AuthTokenConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!config.isAuthorization()) { return true;}
        var b = false;
        var token = request.getHeader(AccessToken.Constant.AUTHORIZATION);
        try {
            if(token != null) {
                var accessToken = JwtUtil.decrypt(token);
                var salt = redis.hget(accessToken.getUid(), AccessToken.Constant.SALT);
                if(salt != null && JwtUtil.validate(token, salt)) {
                    var interval = accessToken.getExp() - Instant.now().getEpochSecond();
                    if(interval >= 0) {
                        b = true;
                    }
                    else if(interval > -config.getSessionExpire() / 2) {
                        //todo 重新颁发token,要让前端知道token已经更新
                    }
                    else {
                        response.setStatus(1000);
                        response.sendRedirect("/40");
                        b = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!b) {
            response.setStatus(HttpStatus.UNAUTHORIZED.getCode());
            try (var writer = response.getWriter() ){
                writer.write(R.response(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED).toResponseBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public void afterRegistry(InterceptorRegistration registration) {
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/user/**", "/swagger-ui.html/**", "/webjars/**", "/swagger-resources/**");
    }


    private String refreshToken(String uid) {

        return "";
    }
}
