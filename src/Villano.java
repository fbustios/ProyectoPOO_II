import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

abstract public class Villano implements Observer {
    protected int nivelInicial;
    protected double velocidad;
    protected boolean atm;
    protected int puntaje;
    protected boolean vivo;
    protected int x;
    protected int y;
    protected int screenX;
    protected int screenY;
    protected Tablero tab;
    protected BufferedImage image;

    public Villano(int x,int y, Tablero tab, int nivelInicial, double velocidad, boolean atm, int puntaje) {
        this.x = x;
        this.y = y;
        this.nivelInicial = nivelInicial;
        this.velocidad = velocidad;
        this.atm = atm;
        this.puntaje = puntaje;
        vivo = true;
        this.tab = tab;
        this.screenX = (y*48);
        this.screenY = (x*48);

    }

     public void update(int x, int y, Graphics2D pincel) {
        moverVillano(x, y,pincel);
    }
    abstract public void moverVillano(int x, int y, Graphics2D pincel);

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

    public BufferedImage getImage(){
        return  image;
    }
    public int getScreenX(){
        return this.screenX;
    }
    public int getScreenY(){
        return this.screenY;
    }
    public void setScreenXY(int X,int Y){
        this.screenX = X;
        this.screenY = Y;
    }
    public boolean getAtm(){
        return this.atm;
    }
}
