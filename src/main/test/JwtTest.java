
import com.deway.blog.entiry.auth.AccessToken;
import com.deway.blog.tool.JwtUtil;
import org.junit.Test;
import java.time.LocalDateTime;

public class JwtTest {

    @Test
    public void encrypt() {
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void decrypt() {
        var token = JwtUtil.encrypt("sdasfdsgjsapfjio", new AccessToken() {
            {
                this.setUserId(1L);
            }

        });
        System.out.println(token);

        JwtUtil.decrypt("sdasfdsgjsapfjio", token);
    }

    @Test
    public void logger() {

    }

}
