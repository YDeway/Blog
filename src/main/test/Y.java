import com.deway.blog.config.GlobalConfig;
import com.deway.blog.mapper.BackGroundImageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitWebConfig
@ContextConfiguration(classes = {GlobalConfig.class})
public class Y {

    @Autowired
    BackGroundImageMapper imageMapper;

    @Test
    public void xx() {
        System.out.println(imageMapper.findById(11L));

    }
}
