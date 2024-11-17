import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Globo extends Villano{
    public Globo(Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(tab,nivelInicial,velocidad,atm,puntaje);
        this.ia = new RandomSystem(this,tab);

        try {
            image0 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\globo0.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\globo111.png"));
        } catch (IOException e) {
            System.out.println("Error al leer el imagen");
            e.printStackTrace();
        }
    }

    @Override
    public void update(int x, int y, Graphics2D pincel) {
        if(!vivo){
            System.out.println("me guardéeeeeeeeeeeeeee");
            pool.release(this);
            return;
        }
        moverVillano(x,y,pincel);
    }

    @Override
    public void moverVillano(int x, int y, Graphics2D pincel) {
        ia.updateGraphics();
        ia.draw(pincel);
    }

}
