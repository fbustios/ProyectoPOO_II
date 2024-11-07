import java.awt.*;

public class Espon extends Villano{

    public Espon(int x,int y, Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(x,y,tab,nivelInicial,velocidad,atm,puntaje);
    }

    @Override
    public void update(int x, int y, Graphics2D pincel) {
        moverVillano(x,y, pincel);
    }

    @Override
    public void moverVillano(int x, int y, Graphics2D pincel) {
    }


}
