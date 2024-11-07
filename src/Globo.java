import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Globo extends Villano{
    VillainMovement rand;
    public Globo(int x,int y, Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(x,y,tab,nivelInicial,velocidad,atm,puntaje);
        this.rand = new RandomSystem(this,tab);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("\\Villains\\globo0.png"));
        } catch (
                IOException e) {
            System.out.println("Error al leer el imagen");
            e.printStackTrace();
        }
    }

    @Override
    public void update(int x, int y, Graphics2D pincel) {
        moverVillano(x,y,pincel);
    }

    @Override
    public void moverVillano(int x, int y, Graphics2D pincel) {
        rand.updateGraphics();
        rand.draw(pincel);
    }

}
