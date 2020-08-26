import org.apache.tika.Tika;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TikaTest {


    @Test
    public void xx() throws IOException {
        var tika = new Tika();
        var file = new File("D:\\Notepad++\\notepad++.exe");
        var detect = tika.detect(file);


        System.out.println(detect);


    }

    @Test
    public void ww() throws IOException {
        //自带的类型的识别是搞笑的
        System.out.println(Files.probeContentType(Path.of("C:\\Users\\D19060004\\Desktop\\ss.exe")));
    }

}
