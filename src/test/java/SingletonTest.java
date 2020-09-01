import com.kevin.ResourceMgr;
import org.junit.Test;

import java.awt.image.BufferedImage;

public class SingletonTest {
    @Test
    public void testSingletonResource() {
        for (int i = 0; i < 100; i++) {
            BufferedImage badTankD = ResourceMgr.INSTANCE.getBadTankD();
            System.out.println(badTankD.hashCode());
        }
    }
}
