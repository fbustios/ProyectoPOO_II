import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.swing.*;
import java.awt.event.ActionEvent;

import java.util.Timer;
import java.util.TimerTask;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import java.util.*;

public class Hero implements Subject{
    private List<Observer> observersList = new ArrayList<>();
    private int vidas;
    private double velocidad;
    private Tablero tablero;
    private int[] posicion = new int[2];
    private static Hero instancia = null;
    private Detonador detonador = new Detonador();
    private boolean muroRayado = false;
    private boolean bombaRayado = false;
    private boolean invulFuego = false;
    private boolean patin = false;
    private GamePanel panel;
    private KeyHandler kh;
    private int X;
    private int Y;
    private boolean canMove = true;
    private BombPool bombas = BombPool.getBombPool();

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

    public void colocarBombaSinDetonador(){
        Bomb bomba = bombas.obtenerBomba();
        System.out.println("Puse una bomba");
        int x = posicion[0]; int y = posicion[1];
        if(bomba!=null){
            tablero.getCoordenada(x, y).setBomb(bomba);
            bomba.setXY(x,y);
            panel.playSoundEffect(3);
            Timer timer2 = new Timer();
            TimerTask task = new TimerTask(){
                @Override
                public void run() {
                    panel.playSoundEffect(1);
                    bomba.explode(tablero);
                    bombas.devolverBomba(bomba);
                    tablero.getCoordenada(x, y).setBomb(null);
                    System.out.println("Exploté");
                    timer2.cancel();
                }
            };
            timer2.schedule(task,2000);
    }}

    public void colocarBombaConDetonador(){
        Bomb bomba = bombas.obtenerBomba();
        int x = posicion[0]; int y = posicion[1];
        if(bomba!=null){
            tablero.getCoordenada(x, y).setBomb(bomba);
            bomba.setXY(x,y);
            detonador.addBomb(bomba);
            panel.playSoundEffect(3);
        }
    }
    public void detonarBomba(){
        detonador.explodeBomb(tablero, panel);

    }

    public boolean move(int dx, int dy) {
        Coordenada n = tablero.getCoordenada(posicion[0] + dx, posicion[1] + dy);
        Coordenada a = tablero.getCoordenada(posicion[0], posicion[1]);
        if (n != null) {
            if (!n.getHayMuro()) {
                n.setHero(this);
                a.setHero(null);
                posicion[0] += dx;
                posicion[1] += dy;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    public void update(){
        if(kh.shiftPressed) {
            if(detonador == null){
                colocarBombaSinDetonador();
            } else {
                colocarBombaConDetonador();
            }
            kh.shiftPressed = false;
        }
        if(kh.spacePressed){
            if(detonador != null){
                detonarBomba();
            }
            kh.spacePressed = false;
        }
        if(kh.upPressed | kh.downPressed | kh.leftPressed | kh.rightPressed){
        if(kh.upPressed){
            direction = "up";
            Y-=velocidad;
            if(Y < posicion[0]*48 - 10){
                canMove = move(-1,0);
                if(!canMove){
                    Y+=velocidad;
                }
            }
        }
        else if(kh.downPressed){
            direction = "down";
            Y+=velocidad;
            if(Y > (posicion[0]+1)*48 - 45){
                canMove =  move(1,  0);
                if(!canMove) {
                    Y -= velocidad;
                }
            }
        }
        else if(kh.leftPressed){
            direction = "left";
            X-=velocidad;
            if(X < posicion[1]*48 - 10){
                canMove = move(0,-1);
                if(!canMove) {
                    X += velocidad;
                }
            }
        }
        else if(kh.rightPressed){
            direction = "right";
            X+=velocidad;
            if(X > (posicion[1]+1)*48 - 40){
                canMove = move(0,1);
                if(!canMove){
                    X-=velocidad;
                }
            }
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
        notifyObservers(posicion[0],posicion[1],pincel);
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
        //notifyObservers(posicion[0],posicion[1],pincel);
        pincel.drawImage(image, X, Y, tablero.getCoordenada(1,1).length, tablero.getCoordenada(1,1).length, null);
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
        observersList.add(observer);
    }
    public void detach(Observer observer){
        observersList.remove(observer);
    }

    public void notifyObservers(int x, int y,Graphics2D pincel){
        for(Observer observer : observersList) observer.update(x,y,pincel);
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
            bombas.actualizarRango(2);
            //set de las bombas del detonador;
        }
    }

}