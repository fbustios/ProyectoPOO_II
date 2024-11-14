import javax.imageio.ImageIO;
import java.awt.*;

public class MonG extends Villano{
    public MonG(Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(tab,nivelInicial,velocidad,atm,puntaje);
        ia = new MinSystem(this,tab);
        try{
            image0 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\MonedaG0.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\MonedaG1.png"));
        } catch (Exception e){
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
        ia.updateGraphics();
        ia.draw(pincel);
    }


}
