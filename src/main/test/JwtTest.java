import com.deway.blog.tool.JwtUtil;
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
        JwtUtil.validate("w.w.w", "");
        System.out.println(JwtUtil.decrypt(token));
    }



}
