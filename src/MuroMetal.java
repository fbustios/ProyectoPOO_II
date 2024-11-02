import javax.imageio.ImageIO;
import java.io.IOException;

public class MuroMetal extends Muro{
    public MuroMetal() {
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("\\Tiles\\Acero1.png"));
        } catch (
                IOException e) {
            System.out.println("Error al leer el imagen");
            e.printStackTrace();
        }
    }
}
