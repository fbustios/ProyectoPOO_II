public class Espon extends Villano{

    public Espon(){
        nivelInicial = 6;
        velocidad = 0.33334;
        atm = true;
        ia = "Lv1";
        puntaje = 1000;
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
