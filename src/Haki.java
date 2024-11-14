import java.awt.*;

public class Haki extends Villano{

    public Haki(Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(tab,nivelInicial,velocidad,atm,puntaje);
    }

    @Override
    public void update(int x, int y, Graphics2D pincel) {
        moverVillano(x,y,pincel);
    }
    public void moverVillano(int x, int y, Graphics2D pincel) {

    }
}
