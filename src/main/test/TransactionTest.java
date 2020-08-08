import com.deway.blog.config.GlobalConfig;
import com.deway.blog.entiry.auth.User;
import com.deway.blog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitWebConfig
@ContextConfiguration(classes = {GlobalConfig.class})
public class TransactionTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() {

        System.out.println(userService.register(new User (){
            {
                this.setPassword("123456");
                this.setUserId("wwww");
            }
        }));
    }


    @Test
    public void login() {

        System.out.println(userService.login(new User (){
            {
                this.setPassword("123456");
                this.setUserId("wwww");
            }
        }));
    }

}
