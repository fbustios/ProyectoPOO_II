import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;


public class Hero implements Subject{
    private List<Observer> observersList;
    private int vidas;
    private double velocidad;
    private Tablero tablero;
    private ArrayList <Observer> observers;
    private int[] posicion = new int[2];
    private static Hero instancia = null;
    private Detonador detonador = null;
    private boolean muroRayado = false;
    private boolean bombaRayado = false;
    private boolean invulFuego = false;
    private boolean patin = false;
    private int cantBombas = 1;
    private GamePanel panel;
    private KeyHandler kh;
    private int X;
    private int Y;
    private int aHorizontal;
    private int aVertical;
    private boolean canMove = true;

    //Variables de impresion
    private BufferedImage atras0, atras1, atras2, frente0, frente1, frente2, izquierda0, izquierda1, izquierda2, derecha0, derecha1, derecha2;
    private String direction = "down";
    private int spriteCounter = 0;
    private int spriteNumber = 1;

    //detonador, moverse y los cupones.

    private Hero(Tablero tab, GamePanel pane, KeyHandler kh){
        vidas = 2;
        posicion[0] = 0;
        posicion[1] = 0;
        tablero = tab;
        velocidad = 1;
        panel = pane;
        X = 0;
        Y = 0;
        this.kh = kh;
        getPlayerImage();
        aHorizontal = 0;
        aVertical = 0;
    }

    public static Hero getInstancia(Tablero tablero, GamePanel pane,KeyHandler kh){
        if(instancia == null){
            instancia = new Hero(tablero, pane,kh);
            return instancia;
        }
        return instancia;
    }

    public boolean restarVida(){
        Coordenada coordenada = tablero.getCoordenada(posicion[0], posicion[1]);
        if(((!invulFuego) && (coordenada.getHayFuego()))|(!(coordenada.getVillano()==null))){
            vidas -= 1;
            detonador = null;
            bombaRayado = false;
            muroRayado = false;
            invulFuego = false;
            return true;
        }
        return false;
    }

    public boolean verificar(int x, int y){
        boolean puedeMoverse = true;
        Coordenada coordenada = tablero.getCoordenada(x, y);
        if(!muroRayado){
           puedeMoverse = !coordenada.getHayMuro();
       }
        if(!bombaRayado){
            if(coordenada.getBomb()==null){
                puedeMoverse = true;
            }
            else{puedeMoverse = false;}
        }
       return puedeMoverse;
    }



    public boolean move(int dx, int dy) {
        System.out.println("dx:" + dx + " " + "dy: " + dy);
        Coordenada n = tablero.getCoordenada(posicion[0] + dx, posicion[1] + dy);
        Coordenada a = tablero.getCoordenada(posicion[0], posicion[1]);
        if (n != null) {
            if (!n.getHayMuro()) {
                n.setHero(this);
                a.setHero(null);
                //notifyObservers(posicion[0]+dx,posicion[1]+dy);
                System.out.println("Estoy en: " + posicion[0] + " " + posicion[1]);
                posicion[0] += dx;
                posicion[1] += dy;
                return true;
            } else {
                //notifyObservers(posicion[0],posicion[1]);
                System.out.println("Estoy en: " + posicion[0] + " " + posicion[1]);
                System.out.println("No me pude mover");
                return false;
            }
        }
        return false;
    }


    public void update(){
        if(kh.upPressed | kh.downPressed | kh.leftPressed | kh.rightPressed){
        if(kh.upPressed){
            direction = "up";
            Y-=velocidad;
            aVertical-=velocidad;
            if(aVertical < 0){  //(aVertical < posicion[1]*48 - un poco menos de la altura 20 o 30)
                aVertical = 48;
                System.out.println("Cambie de casilla arriba");
                canMove = move(-1,0);
                if(!canMove){
                    Y+=velocidad;}

            }
        }
        else if(kh.downPressed){
            direction = "down";
            Y+=velocidad;
            aVertical+=velocidad;
            if(aVertical > 48){ //(aVertical > (posicion[1]+1)*48 - altura 40)
                System.out.println("Cambie de casilla abajo");
                canMove =  move(1,  0);
                if(!canMove){
                    Y-=velocidad;
                    aVertical-=velocidad;
                } else {
                    aVertical = 0;
                }



            }
        }
        else if(kh.leftPressed){
            direction = "left";
            X-=velocidad;
            aHorizontal -= velocidad;
            if(aHorizontal < 0){ //(aHorizontal < posicion[0]*48 - rango de error 8)
                aHorizontal = 48;
                System.out.println("Cambie de casilla a la izquierda");
                canMove = move(0,-1);
                if(!canMove){
                    X+=velocidad;
                }
            }
        }
        else if(kh.rightPressed){
            direction = "right";
            X+=velocidad;
            aHorizontal += velocidad;
            if(aHorizontal > 18){ //( > (posicion[0]+1)*48 - ancho del personaje 40)
                aHorizontal = 0;
                System.out.println("Cambie de casilla a la derecha");
                canMove = move(0,1);
                if(!canMove){
                    X-=velocidad;
                }
            }
             //falta sincronizarlo bien...
        }
        //mueve la animacion
        spriteCounter ++;
        if(spriteCounter > 12){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber = 3;
            }
            else if(spriteNumber == 3){
                spriteNumber = 1;
            }
            spriteCounter = 0;}
        }
    }

    public void getPlayerImage(){

        try{
            atras0 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje atrás0.png"));
            atras1 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje atrás1.png"));
            atras2 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje atrás2.png"));
            frente0 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje frente0.png"));
            frente1 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje frente1.png"));
            frente2 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje frente2.png"));
            izquierda0 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje izquierda0.png"));
            izquierda1 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje izquierda1.png"));
            izquierda2 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje izquierda2.png"));
            derecha0 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje derecha0.png"));
            derecha1 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje derecha1.png"));
            derecha2 = ImageIO.read(getClass().getResourceAsStream("\\Hero\\Personaje derecha2.png"));

        }catch(IOException e){
            System.out.println("Error al leer el imagen");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D pincel){
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNumber==1){image = atras0;}
                if(spriteNumber==2){image = atras1;}
                if(spriteNumber==3){image = atras2;}
                break;
            case "down":
                if(spriteNumber==1){image = frente0;}
                if(spriteNumber==2){image = frente1;}
                if(spriteNumber==3){image = frente2;}
                break;
            case "left":
                if(spriteNumber==1){image = izquierda0;}
                if(spriteNumber==2){image = izquierda1;}
                if(spriteNumber==3){image = izquierda2;}
                break;
            case "right":
                if(spriteNumber==1){image = derecha0;}
                if(spriteNumber==2){image = derecha1;}
                if(spriteNumber==3){image = derecha2;}
                break;
        }
        //System.out.println("X: " + X + " " + "Y: " + Y);
        pincel.drawImage(image, X, Y, tablero.getCoordenada(1,1).length-40, tablero.getCoordenada(1,1).length-40, null);
    }

    public int getVidas(){
        return vidas;
    }
    public void setVidas(int vidas){
        this.vidas = vidas;
    }
    public int[] getPosicion(){
        return posicion;
    }
    public void attach(Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(int x, int y){
        for(Observer observer : observersList) observer.update(x,y,panel);
    }
    public void checkState(){
        Coordenada a = tablero.getCoordenada(posicion[0],posicion[1]);
        if(restarVida()){
            return;
        }
        if(a.getCuponPatin()){
            this.patin = true;
            this.velocidad += 1.5;
            a.setCuponPatin(false);
        }
        if(a.getCuponMuroRayado()){
            this.muroRayado = true;
            a.setCuponMuroRayado(false);
        }
        if(a.getCuponBombaRayado()){
            this.bombaRayado = true;
            a.setCuponBombaRayado(false);
        }
        if(a.getCuponHombreLlamas()){
            this.invulFuego = true;
            a.setCuponHombreLlamas(false);
        }
        if(a.getCuponPregunta()){
            this.invulFuego = true; //falta lo del tiempo;
            a.setCuponPregunta(false);
        }
        if(a.getCuponSol()){
            a.setCuponSol(false);
            this.cantBombas+=2;
            //set de las bombas del detonador;
        }
    }

}