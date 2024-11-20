import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Globo extends Villano{
    int cont = 2;
    private int num = 1;
    public Globo(Tablero tab, int nivelInicial,double velocidad,int puntaje, boolean atm){
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
    public void update(int x, int y, Graphics2D pincel, GamePanel panel) {
        if(!vivo){
            System.out.println("me guardéeeeeeeeeeeeeee");
            panel.mensajes.mostrarMensaje(String.valueOf(puntaje));
            ScoreBoard.getInstance().sumScore(puntaje,num);
            panel.playSoundEffect(7);
            pool.release(this); cont = 2;
            return;
        }
        moverVillano(x,y,pincel,panel);
    }

    @Override
    public void moverVillano(int x, int y, Graphics2D pincel,GamePanel panel) {
        if (panel.gameState == 1) {
            if (cont == 0) {
                ia.updateGraphics();
                cont = 2;
            }
            ia.draw(pincel);
            cont--;
        }else{ia.draw(pincel);}
    }

}
