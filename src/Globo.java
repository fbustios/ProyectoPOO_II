public class Globo extends Villano{
    VillainMovement rand;
    public Globo(int x,int y, Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(x,y,tab,nivelInicial,velocidad,atm,puntaje);
        this.rand = new RandomSystem(this,tab);
    }

    @Override
    public void update(int x, int y) {
        moverVillano(x,y);
    }

    @Override
    public void moverVillano(int x, int y) {
        rand.updateGraphics();
        //rand.draw();
    }


}
