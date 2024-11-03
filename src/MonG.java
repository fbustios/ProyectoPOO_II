public class MonG extends Villano{

    public MonG(){
        nivelInicial = 14;
        velocidad = 1.5;
        atm = true;
        ia = "Lv4";
        puntaje = 4000;
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
