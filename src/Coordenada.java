import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Coordenada{
    private Hero hero = null;
    private Bomb bomb = null;
    private MuroMetal muroMetal;
    private MuroLadrillos muroLadrillo;
    private boolean hayFuego;
    private boolean hayMuro;
    private int x;
    private int y;
    private boolean cuponMuroRayado = false;
    private boolean cuponBombaRayado = false;
    private boolean hayCupon = false;
    private boolean cuponSol = false;
    private boolean cuponPatin = false;
    private boolean cuponPregunta = false;
    private boolean cuponHombreLlamas = false;
    private String tipoMuro = "Cesped";
    private Villano villano;
    private BufferedImage Cesped,CespedFloreado;
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
    }

    public void getCoordenadaImage(){
        try{
            Cesped = ImageIO.read(getClass().getResourceAsStream("\\Tiles\\Cesped1.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D pincel){

        BufferedImage image = null;
        if(muroLadrillo!=null) {
            image = muroLadrillo.getImagen();
            pincel.drawImage(image, y * 48, x * 48, 48, 48, null);
        }else if(muroMetal != null){
            pincel.drawImage(muroMetal.imagen, y*48, x*48,48, 48, null);
        } else if(hayFuego){
            pincel.drawImage(image, y*48, x*48, 48, 48, null);
            image = null;
        } else if(!hayMuro && hayCupon){
            pincel.drawImage(image, y*48, x*48, 48, 48, null);
            image = null;
        } else{
            image = Cesped;
            pincel.drawImage(image, y*48, x*48, 48, 48, null);
        }
    }

    public boolean getHayMuro(){
        return this.hayMuro;
    }

    public void setMuroMetal(){
        this.muroMetal = new MuroMetal();
        this.hayMuro = true;

    }
    public void setHero(Hero hero){
        this.hero = hero;
    }

    public void setMuroLadrillo(){
        this.muroLadrillo = new MuroLadrillos();
        hayMuro = true;
    }

    public boolean getHayFuego(){
        return this.hayFuego;
    }

    public void setHayFuego(boolean t){
        this.hayFuego = t;
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

    public void setPatin(boolean patin){
        cuponPatin = patin;
    }

    public void setPregunta(boolean pregunta ){
        cuponPregunta = pregunta;
    }

}
