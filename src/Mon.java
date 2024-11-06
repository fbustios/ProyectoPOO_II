public class Mon extends Villano{

    public Mon(int x,int y, Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(x,y,tab,nivelInicial,velocidad,atm,puntaje);
    }

    @Override
    public void update(int x, int y) {
        moverVillano(x,y);
    }

    public void moverVillano(int x, int y) {
    }
}
