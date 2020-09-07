import com.kevin.ResourceMgr;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Assert;
import org.junit.Test;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ImageTest {
    @Test
    public void test() {
        try {
            BufferedImage image = ImageIO.read(new File("F:\\projects\\tank_war\\src\\main\\resources\\images\\4.gif"));
            assertNotNull(image);

            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image2);

            BufferedImage image3 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image3);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(0);
        objects.add(1);
        objects.add(2);
        objects.add(3);
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j <objects.size() ; j++) {
                System.out.println(i+"-->"+j);
            }
        }
    }

}
