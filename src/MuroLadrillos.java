import javax.imageio.ImageIO;
import java.io.IOException;

public class MuroLadrillos extends Muro {
    public MuroLadrillos() {
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("\\Tiles\\Ladrillo.png"));
        } catch (
                IOException e) {
            System.out.println("Error al leer el imagen");
            e.printStackTrace();
        }
    }
}

