
import com.deway.blog.entiry.auth.AccessToken;
import com.deway.blog.tool.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.HashMap;

public class JwtTest {

    @Test
    public void encrypt() {
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void decrypt() throws Exception {


        var token = JwtUtil.encrypt(new HashMap<>() {
            {
                put("uid", "11");
            }
        }, "sdasfdsgjsapfjio", 12800);

        System.out.println(JwtUtil.decrypt("sdasfdsgjsapfjio", token));
    }

    @Test
    public void logger() {

    }

}
