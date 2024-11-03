public class Cel extends Villano{

    public Cel(){
        nivelInicial = 2;
        velocidad = 1;
        atm = false;
        ia = "Rnd";
        puntaje = 200;
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
