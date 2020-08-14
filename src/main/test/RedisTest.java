import com.deway.blog.config.GlobalConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringJUnitWebConfig
@ContextConfiguration(classes = {GlobalConfig.class})
public class RedisTest {

    @Autowired
    private Jedis redis;

    @Test
    public void xx() {
        System.out.println(redis.expire("sdas",1000));
    }

}
