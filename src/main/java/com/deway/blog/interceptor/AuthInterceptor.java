package com.deway.blog.interceptor;

import com.deway.blog.config.AuthTokenConfig;
import com.deway.blog.entiry.auth.AccessToken;
import com.deway.blog.tool.HttpStatus;
import com.deway.blog.tool.JwtConstant;
import com.deway.blog.tool.JwtTokenUtil;
import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证<br>
 * 被排除的请求路径处理不会刷新token过期时间
 * @author Deway
 */
@Component
@AllArgsConstructor
public class AuthInterceptor implements Interceptor {

    private final Jedis redis;
    private final AuthTokenConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!config.isAuthorization()) { return true;}
        var b = false;
        var reqToken = request.getHeader(JwtConstant.AUTHORIZATION);
        if(reqToken != null) {
            var accessToken = JwtTokenUtil.decrypt(reqToken, AccessToken.class);
            if(redis.exists(accessToken.getUid())) {
                b = JwtTokenUtil.validate(reqToken, redis.hget(accessToken.getUid(), JwtConstant.SALT));
            }
            if(b) {
                b = redis.expire(accessToken.getUid(), config.getSessionExpire()) != 0;
            }
        }

        if(!b) {
            response.setStatus(HttpStatus.UNAUTHORIZED.getCode());
            var writer = response.getWriter();
            writer.write(
                     R
                    .response(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED)
                    .toResponseBody()
            );
            writer.close();
        }
        return b;
    }

    @Override
    public void afterRegistry(InterceptorRegistration registration) {
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/user/**", "/swagger-ui.html/**", "/webjars/**", "/swagger-resources/**");
    }

}
