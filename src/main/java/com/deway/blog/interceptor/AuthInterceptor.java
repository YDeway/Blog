package com.deway.blog.interceptor;

import com.deway.blog.config.AuthTokenConfig;
import com.deway.blog.entity.auth.AccessToken;
import com.deway.blog.tool.JwtConstant;
import com.deway.blog.tool.JwtTokenUtil;
import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * 登录验证<br>
 * @todo 被排除的请求路径处理不会刷新token过期时间
 * @author Deway
 */
@Component
@AllArgsConstructor
public class AuthInterceptor implements Interceptor {

    private final Jedis redis;
    private final AuthTokenConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!config.isAuthorized()) { return true;}
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
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
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
        registration.addPathPatterns("/**")
            .excludePathPatterns("/","/user/**", "/swagger-ui.html/**", "/webjars/**", "/swagger-resources/**")
            //数字越小越先执行，似乎负数也行，没做太多的验证
            .order(-1);
    }

}
