import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class Other {

    @Test
    public void a() {
        new A();
    }
}


class A {

    int a ;
    {
        System.out.println(a);
    }
    A(){
        a = 1;
        System.out.println("A");
    }

}