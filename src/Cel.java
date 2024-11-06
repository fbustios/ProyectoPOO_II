public class Cel extends Villano{

    public Cel(int x,int y, Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(x,y,tab,nivelInicial,velocidad,atm,puntaje);
    }

    @Override
    public void update(int x, int y) {
        moverVillano(x,y);
    }

    @Override
    public void moverVillano(int x, int y) {

    }
}
