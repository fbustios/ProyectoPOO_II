public class Mon extends Villano{

    public Mon(){
        nivelInicial = 11;
        velocidad = 1.5;
        atm = false;
        ia = "Lv3";
        puntaje = 3000;
        vivo = true;
    }

    @Override
    public void update(int x, int y, GamePanel panel) {
        moverVillano(x,y,panel);
    }

    public void moverVillano(int x, int y, GamePanel panel) {
    }
}
