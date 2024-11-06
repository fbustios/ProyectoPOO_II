import java.util.Observable;

abstract public class Villano implements Observer {
    protected int nivelInicial;
    protected double velocidad;
    protected boolean atm;
    protected int puntaje;
    protected boolean vivo;
    protected int x;
    protected int y;
    protected Tablero tab;

    public Villano(int x,int y, Tablero tab, int nivelInicial, double velocidad, boolean atm, int puntaje) {
        this.x = x;
        this.y = y;
        this.nivelInicial = nivelInicial;
        this.velocidad = velocidad;
        this.atm = atm;
        this.puntaje = puntaje;
        vivo = true;
        this.tab = tab;

    }

     public void update(int x, int y) {
        moverVillano(x, y);
    }
    abstract public void moverVillano(int x, int y);

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }


    public double getVelocidad(){
        return this.velocidad;
    }
}
