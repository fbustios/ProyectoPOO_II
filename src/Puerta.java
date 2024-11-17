import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Puerta {
    private boolean abierta = false;
    private boolean descubierta = false;
    private int x;
    private int y;

    public Puerta(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }
    public void setDescubiera(boolean descubierta) {
        this.descubierta = descubierta;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
