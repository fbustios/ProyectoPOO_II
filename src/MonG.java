import javax.imageio.ImageIO;
import java.awt.*;

public class MonG extends Villano{
    int cont = 1;
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
    public void update(int x, int y, Graphics2D pincel) {
        if(!vivo){
            ia = new MinSystem(this,tab);
            System.out.println("me guardéeeeeeeeeeeeeee");
            if(!extra) pool.release(this); cont = 1;
            return;
        }
        moverVillano(x,y,pincel);
    }

    @Override
    public void moverVillano(int x, int y, Graphics2D pincel) {
        if(cont==0) {
            ia.updateGraphics();
            cont = 1;
        }
        ia.draw(pincel);
        cont--;
    }


}
