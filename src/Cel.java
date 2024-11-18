import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Cel extends Villano{
    int cont = 2;
    private int num = 1;
    public Cel(Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(tab,nivelInicial,velocidad,atm,puntaje);
        this.ia = new RandomSystem(this,tab);
        try {
            image0 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Oso0.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Oso1.png"));
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
            pool.release(this); cont = 2;
            return;
        }
        moverVillano(x,y,pincel,panel);
    }

    @Override
    public void moverVillano(int x, int y, Graphics2D pincel, GamePanel panel) {
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
