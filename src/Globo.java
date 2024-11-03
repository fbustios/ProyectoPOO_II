public class Globo extends Villano{

    public Globo(){
        nivelInicial = 1;
        velocidad = 0.66667;
        atm = false;
        ia = "Rnd";
        puntaje = 100;
        vivo = true;
    }

    @Override
    public void update(int x, int y, GamePanel panel) {
        moverVillano(x,y,panel);
    }

    @Override
    public void moverVillano(int x, int y, GamePanel panel) {

    }
}
