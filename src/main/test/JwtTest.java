import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

public class JwtTest {

    @Test
    public void encrypt() {
        JwtBuilder builder= Jwts.builder()

                .setId("k0")
                .setSubject("v0")

                .claim("k1", "v2")
                .claim("k2", "v2")

                .setIssuedAt(new Date())//设置签发时间
                .signWith(SignatureAlgorithm.HS256,"xiaocai")
                .setExpiration(new Date(new Date().getTime() + 60 * 1000));//设置签名秘钥
        System.out.println( builder.compact() );
    }

    @Test
    public void decrypt() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1OTY4Njk4NjEsImV4cCI6MTU5Njg2OTkyMX0.-gpwpOv51pqMleW6ChyyzADkXibGQq8lNXY173aQfUQ";
        Claims claims = Jwts
                .parser()
                .setSigningKey("xiaocai")
                .parseClaimsJws(token)
                .getBody();

        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("IssuedAt:"+claims.getIssuedAt());
        System.out.println("IssuedAt:"+claims.getExpiration());

    }

}
