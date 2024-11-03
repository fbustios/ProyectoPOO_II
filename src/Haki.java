public class Haki extends Villano{

    public Haki(){
        nivelInicial = 3;
        velocidad = 1.33334;
        atm = false;
        ia = "Lv2";
        puntaje = 400;
        vivo = true;
    }

    @Override
    public void update(int x, int y, GamePanel panel) {
        moverVillano(x,y,panel);
    }
    public void moverVillano(int x, int y, GamePanel panel) {

    }
}
