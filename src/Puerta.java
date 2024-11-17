import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Puerta {
    private boolean abierta = false;
    private boolean descubierta = false;
    private int x;
    private int y;

    public Puerta() {
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }
    public void setDescubiera(boolean descubierta) {
        this.descubierta = descubierta;
    }
}
