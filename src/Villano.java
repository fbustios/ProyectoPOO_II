import java.util.Observable;

abstract public class Villano implements Observer {
    protected int nivelInicial;
    protected double velocidad;
    protected boolean atm;
    protected String ia;
    protected int puntaje;
    protected boolean vivo;

    public void update(int x, int y, GamePanel panel) {
        moverVillano(x, y, panel);
    }
    abstract public void moverVillano(int x, int y, GamePanel panel);
}
