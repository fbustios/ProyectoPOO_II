import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

abstract public class Villano implements Observer {
    protected VillainMovement ia;
    protected VillainPool pool;
    protected int nivelInicial;
    protected double velocidad;
    protected boolean atm;
    protected int puntaje;
    protected boolean vivo;
    protected int x = 0;
    protected int y = 0;
    protected int screenX = 0;
    protected int screenY = 0;
    protected boolean extra;
    protected Tablero tab;
    protected BufferedImage image, image0, image1;
    protected Hero hero;
    protected boolean active;

    public Villano(Tablero tab, int nivelInicial, double velocidad, boolean atm, int puntaje) {
        this.nivelInicial = nivelInicial;
        this.velocidad = velocidad;
        this.atm = atm;
        this.puntaje = puntaje;
        vivo = true;
        this.tab = tab;
        this.pool = VillainPool.getInstancia();
        this.vivo = true;
        this.active = false;
        this.extra = false;
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

    public BufferedImage getImage0(){
        return  image0;
    }

    public BufferedImage getImage1(){
        return  image1;
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
        this.ia.setScreenXY(X,Y);
    }
    public boolean getAtm(){
        return this.atm;
    }

    public int getNivelInicial(){
        return this.nivelInicial;
    }
    public boolean isVivo(){
        return this.vivo;
    }

    public void reset(){
        this.vivo= true;
        this.x = 0;
        this.y = 0;
        this.screenY = 0;
        this.screenX = 0;
        this.active = false;
    }
    public void setActive(boolean t){
        this.active = t;
    }
    public boolean isActive(){
        return this.active;
    }

    public void setIa(VillainMovement s){
        this.ia = s;
    }

    public boolean isExtra(){
        return this.extra;
    }
}
