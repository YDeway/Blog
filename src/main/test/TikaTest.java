import org.apache.tika.Tika;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class TikaTest {


    @Test
    public void xx() throws IOException {
        var tika = new Tika();
        var file = new File("C:\\Users\\D19060004\\Desktop\\ss");
        var detect = tika.detect(file);


        System.out.println(detect.substring(detect.indexOf("/") + 1));


    }
}
