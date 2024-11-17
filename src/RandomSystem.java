import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class RandomSystem implements VillainMovement {
    private boolean canMove = true;
    private String direction = "hola";
    private String d = "Down";
    private Villano v;
    private Tablero tablero;
    private int cont = 48;
    private int spriteCounter = 0;
    private int spriteNumber = 1;
    private int screenX;
    private int screenY;

    RandomSystem(Villano villano, Tablero tablero){
        this.v = villano;
        this.tablero = tablero;
        this.screenX = v.getScreenX();
        this.screenY = v.getScreenY();
    }

    public boolean move(int dx, int dy) {
        Coordenada n = tablero.getCoordenada(v.getX() + dx,  v.getY() + dy);
        Coordenada a = tablero.getCoordenada(v.getX(), v.getY());
        if (n != null) {
            if (!n.getHayMuro() || (v.getAtm()&& n.getMuroMetal()==null) && n.getBomb() == null) {
                n.setVillano(v);
                a.setVillano(null);
                v.setXY(v.getX()+dx, v.getY()+dy);

                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void updateGraphics() {
        if(v.isActive()){
        Random rand = new Random();
        int x = v.getX();
        int y = v.getY();
        screenX = v.getScreenX();
        screenY = v.getScreenY();

        double velocidad = v.getVelocidad();
        Coordenada a = tablero.getCoordenada(v.getX(), v.getY());
        if(a.getHayFuego()){
            v.setVivo(false);
            a.setVillano(null);
            System.out.println("Me morí");
        }

        if(cont == 48){
            int i = rand.nextInt(0,4);
            if(i == 0){d = "Up";}
            else if(i == 1){d = "Down";}
            else if(i == 2){d = "Left";}
            else if(i == 3){d = "Right";}
            cont = 0;
        }
        if (d == "Up") {
            direction = "up";
            screenY -= velocidad;

            if (screenY < x * 48 - 10) {
                canMove = move(-1,0);
                if (!canMove) {
                    screenY += velocidad;
                }
            }
        } else if (d == "Down") {
            direction = "down";
            screenY += velocidad;
            if (screenY > (x + 1) * 48 - 55) {
                canMove = move(1,0);
                //System.out.println("Cambie de casilla abajo");
                if (!canMove) {
                    screenY -= velocidad;
                }
            }
        } else if (d  == "Left") {
            direction = "left";
            screenX -= velocidad;
            if (screenX < y * 48 - 10) {
                canMove = move(0,-1);
                //System.out.println(canMove);
                if (!canMove) {
                    screenX += velocidad;
                }
            }
        } else if (d == "Right") {
            direction = "right";
            screenX += velocidad;
            if (screenX > (y + 1) * 48 - 40) {
                canMove = move(0,1);
                //System.out.println("Cambie de casilla a la derecha");
                if (!canMove) {
                    screenX -= velocidad;
                }
            }
        }
        v.setScreenXY(screenX, screenY);
        cont++;

        spriteCounter ++;
        if(spriteCounter > 48){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber = 1;
            }

            spriteCounter = 0;}

    }}

    public void draw(Graphics2D pincel){
        //System.out.println(v.getX() + " " + v.getY());
        //System.out.println(screenX + " " + screenY);
        BufferedImage image = null;

        if(spriteNumber==1){image = v.getImage0();}
        if(spriteNumber==2){image = v.getImage1();}


        //pincel.setColor(Color.yellow);
        //pincel.fillRect(v.getScreenX(), v.getScreenY(), tablero.getCoordenada(1,1).length, tablero.getCoordenada(1,1).length);

        if(v.isActive())pincel.drawImage(image, screenX, screenY, tablero.getCoordenada(1,1).length, tablero.getCoordenada(1,1).length, null);

    }

    public void setScreenXY(int x, int y){
        this.screenX = x;
        this.screenY = y;
    }

}

