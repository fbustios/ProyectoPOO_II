import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class  Muro {
    protected boolean roto;
    protected BufferedImage imagen;

    public Muro(){
        roto = false;
    }

    public BufferedImage getImagen(){
        return imagen;
    }
}
