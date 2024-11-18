import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

public class Haki extends Villano{
    private VillainMovement ia2;
    private int cont = 400;
    private static Random rand = new Random();
    private int r = rand.nextInt(0,100);

    public Haki(Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(tab,nivelInicial,velocidad,atm,puntaje);
        ia = new MinSystem(this,tab);
        ia2 = new RandomSystem(this,tab);
        try{
            image0 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Fantasma0.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Fantasma1.png"));
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

    public void moverVillano(int x, int y, Graphics2D pincel, GamePanel panel) {
        if (panel.gameState == 1) {
            Random rand = new Random();
            if (cont == 400) {
                r = rand.nextInt(0, 100);
                cont = 0;
            }
            if (r > 75) {
                ia.updateGraphics();
                //ia.draw(pincel);
            } else {
                ia2.updateGraphics();
                //ia2.draw(pincel);
            }
            ia.draw(pincel);
            cont++;
            System.out.println("x: " + this.x + " " + "y: " + this.y);
        }else{ia.draw(pincel);}
    }
}
