import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static java.lang.Math.abs;

public class Mon extends Villano{
    VillainMovement ia2;
    public Mon(Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(tab,nivelInicial,velocidad,atm,puntaje);
        ia = new MinSystem(this,tab);
        ia2 = new RandomSystem(this,tab);
        try{
            image0 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Moneda0.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Moneda1.png"));
        } catch (IOException e){
            System.out.println("Error al leer la imagen");
            e.printStackTrace();
        }
    }

    @Override
    public void update(int x, int y, Graphics2D pincel, GamePanel panel) {
        if(!vivo){
            System.out.println("me guardéeeeeeeeeeeeeee");
            pool.release(this);
            return;
        }
        moverVillano(x,y,pincel,panel);
    }

    public void moverVillano(int x, int y, Graphics2D pincel,GamePanel panel) {
        if (panel.gameState == 1) {
            if (abs(x - this.x) < 7 && (y - this.y) < 7) {
                ia.updateGraphics();
                //ia.draw(pincel);
            } else {
                ia2.updateGraphics();
                //ia2.draw(pincel);
            }
            ia.draw(pincel);
            System.out.println("x: " + this.x + " " + "y: " + this.y);
        }else{ia.draw(pincel);}
    }
}
