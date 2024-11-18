import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Coordenada{
    private Hero hero = null;
    private Bomb bomb = null;
    private Puerta puerta = null;
    private MuroMetal muroMetal;
    private MuroLadrillos muroLadrillo;
    private boolean hayFuego;
    private boolean hayMuro;
    private int x;
    private int y;
    private boolean cuponMuroRayado = false;
    private boolean cuponBombaRayado = false;
    private boolean cuponBombaDorada = false;
    private boolean hayCupon = false;
    private boolean cuponSol = false;
    private boolean cuponPatin = false;
    private boolean cuponPregunta = false;
    private boolean cuponHombreLlamas = false;
    private boolean hayDetonador = false;
    private String tipoMuro = "Cesped";
    private Villano villano;
    private BufferedImage Cesped,CespedFloreado, Fuego, Bomba, Door, Coupon;
    int length = 48;
    int area = 48*48;  //area de cada coordenada 48 de largo y 48 ancho

    public Coordenada(int x, int y){
        this.muroMetal = null;
        this.muroLadrillo = null;
        this.hayFuego = false;
        this.hayMuro = false;
        this.x = x;
        this.y = y;
        this.villano = null;
        getCoordenadaImage();
    }

    public void getCoordenadaImage(){
        try{
            Cesped = ImageIO.read(getClass().getResourceAsStream("\\Tiles\\Cesped1.png"));
            CespedFloreado = ImageIO.read(getClass().getResourceAsStream("\\Tiles\\CespedFloreado.png"));
            Fuego = ImageIO.read(getClass().getResourceAsStream("\\Things\\Bomba14.png"));
            Bomba = ImageIO.read(getClass().getResourceAsStream("\\Things\\BombaCesped.png"));
            Door = ImageIO.read(getClass().getResourceAsStream("\\Things\\Puerta.png"));
            Coupon = ImageIO.read(getClass().getResourceAsStream("\\Things\\CuponDorado.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D pincel){

        BufferedImage image = null;
        if(muroLadrillo!=null) {
            image = muroLadrillo.getImagen();
            pincel.drawImage(image, y * 48, x * 48, 48, 48, null);
        }else if(muroMetal != null) {
            pincel.drawImage(muroMetal.imagen, y * 48, x * 48, 48, 48, null);
        }else if(bomb!=null){
            image = Bomba;
            pincel.drawImage(image, y*48, x*48, 48, 48, null);
        } else if(hayFuego) {
            image = Fuego;
            pincel.drawImage(image, y * 48, x * 48, 48, 48, null);
        }else if(puerta!=null) {
            image = Door;
            pincel.drawImage(image, y * 48, x * 48, 48, 48, null);
        } else{
            image = Cesped;
            pincel.drawImage(image, y*48, x*48, 48, 48, null);
        }
        if(!hayMuro && hayCupon){
            System.out.println("Pintando cupon");
            image = Coupon;
            pincel.drawImage(image, y*48, x*48, 48, 48, null);
    }}


    public boolean getHayMuro(){
        return this.hayMuro;
    }

    public void setMuroMetal(){
        this.muroMetal = new MuroMetal();
        this.hayMuro = true;
    }
    public MuroMetal getMuroMetal(){
        return this.muroMetal;
    }

    public void setHero(Hero hero){
        this.hero = hero;
    }

    public void setMuroLadrillo(){
        this.muroLadrillo = new MuroLadrillos();
        hayMuro = true;
    }
    public MuroLadrillos getMuroLadrillo(){
        return this.muroLadrillo;
    }

    public void setPuerta(Puerta puerta){
        this.puerta = puerta;
    }
    public Puerta getPuerta(){
        return puerta;
    }

    public boolean getHayFuego(){
        return this.hayFuego;
    }

    public void setHayFuego(int milisegundos){
        this.hayFuego = true;
        this.muroLadrillo = null;
        this.hayMuro = false;
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                hayFuego = false;
                timer.cancel();
            }
        };
        timer.schedule(task,milisegundos);
    }

    public void setBomb(Bomb bomb){
        this.bomb = bomb;
    }

    public Bomb getBomb(){
        return this.bomb;
    }

    public Villano getVillano(){
        return this.villano;
    }

    public void setVillano(Villano villano) {
        this.villano = villano;
    }

    public void setCuponBombaDorada(boolean t){
        this.cuponBombaDorada = t;
    }
    public boolean getCuponBombaDorada(){
        return this.cuponBombaDorada;
    }

    public void setCuponBombaRayado(boolean cuponBombaRayado) {
        this.cuponBombaRayado = cuponBombaRayado;
    }

    public boolean getCuponBombaRayado(){
        return this.cuponBombaRayado;
    }
    public void setCuponSol(boolean cuponSol) {
        this.cuponSol = cuponSol;
    }

    public boolean getCuponSol(){
        return this.cuponSol;
    }

    public void setCuponPatin(boolean cuponPatin) {
        this.cuponPatin = cuponPatin;
    }
    public boolean getCuponPatin(){
        return this.cuponPatin;
    }
    public void setCuponPregunta(boolean cuponPregunta) {
        this.cuponPregunta = cuponPregunta;
    }
    public boolean getCuponPregunta(){
        return this.cuponPregunta;
    }
    public void setCuponHombreLlamas(boolean cuponHombreLlamas) {
        this.cuponHombreLlamas = cuponHombreLlamas;
    }
    public boolean getCuponHombreLlamas(){
        return this.cuponHombreLlamas;
    }

    public boolean getCuponMuroRayado(){
        return this.cuponMuroRayado;
    }

    public void setCuponMuroRayado(boolean cuponMuroRayado) {
        this.cuponMuroRayado = cuponMuroRayado;
    }

    public void setHayDetonador(boolean t){
        this.hayDetonador = t;
    }
    public boolean hayDetonador(){
        return this.hayDetonador;
    }

    public boolean getHayCupon(){return hayCupon;}

    public void setHayCupon(boolean hayCupon){
        this.hayCupon = hayCupon;
    }


    public boolean hayHero(){
        return this.hero != null;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

}
