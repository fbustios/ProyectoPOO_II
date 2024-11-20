import javax.imageio.ImageIO;
import java.awt.*;

public class MonG extends Villano{
    int cont = 1;
    private int num = 1;
    public MonG(Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm, boolean extra){
        super(tab,nivelInicial,velocidad,atm,puntaje);
        ia = new MinSystem(this,tab);
        this.extra = extra;
        try{
            image0 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\MonedaG0.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\MonedaG1.png"));
        } catch (Exception e){
            System.out.println("Error al leer el imagen");
            e.printStackTrace();

        }
    }

    @Override
    public void update(int x, int y, Graphics2D pincel, GamePanel panel) {
        if(!vivo){
            ia = new MinSystem(this,tab);
            System.out.println("me guardéeeeeeeeeeeeeee");
            panel.mensajes.mostrarMensaje(String.valueOf(puntaje));
            ScoreBoard.getInstance().sumScore(puntaje,num);
            panel.playSoundEffect(7);
            if(!extra) pool.release(this); cont = 1;
            return;
        }
        moverVillano(x,y,pincel,panel);
    }

    @Override
    public void moverVillano(int x, int y, Graphics2D pincel, GamePanel panel) {
        if (panel.gameState == 1) {
            if (cont == 0) {
                ia.updateGraphics();
                cont = 1;
            }
            ia.draw(pincel);
            cont--;
        }else{ia.draw(pincel);}
    }

}
