import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static java.lang.Math.abs;

public class Mon extends Villano{
    VillainMovement ia;
    VillainMovement ia2;
    public Mon(int x,int y, Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(x,y,tab,nivelInicial,velocidad,atm,puntaje);
        this.ia = new MinSystem(this,tab);
        this.ia2 = new RandomSystem(this,tab);
        try{
            image = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Moneda0.png"));
        } catch (IOException e){
            System.out.println("Error al leer la imagen");
            e.printStackTrace();
        }
    }

    @Override
    public void update(int x, int y, Graphics2D pincel) {
        moverVillano(x,y, pincel);
    }

    public void moverVillano(int x, int y, Graphics2D pincel) {
        if(abs(x - this.x) < 7 && (y - this.y) < 7){
            ia.updateGraphics();
            ia.draw(pincel);
        } else {
            ia2.updateGraphics();
            ia2.draw(pincel);
        }
    }
}
