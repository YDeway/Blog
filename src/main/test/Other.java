import org.junit.Test;

import java.time.Instant;

public class Other {

    @Test
    public void a() {
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(System.currentTimeMillis() / 1000);
    }
}
