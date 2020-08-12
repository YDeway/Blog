import com.deway.blog.tool.Pbkdf2Util;
import com.deway.blog.tool.RandomSalt;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Encrypt {


    @Test
    public void xx() throws NoSuchAlgorithmException, InvalidKeySpecException {

        String encrypt = Pbkdf2Util.encrypt("5efprvrtvv5453grwds ", RandomSalt.randomSalt().getBytes());
        System.out.println(encrypt);
        System.out.println(encrypt.length());
    }
}