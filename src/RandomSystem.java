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

    RandomSystem(Villano villano, Tablero tablero){
        this.v = villano;
        this.tablero = tablero;
    }

    public boolean move(int dx, int dy) {
        Coordenada n = tablero.getCoordenada(v.getX() + dx,  v.getY() + dy);
        Coordenada a = tablero.getCoordenada(v.getX(), v.getY());
        if (n != null) {
            if (!n.getHayMuro()) {
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
        Random rand = new Random();
        int x = v.getX();
        int y = v.getY();
        int screenX = v.getScreenX();
        int screenY = v.getScreenY();

        double velocidad = v.getVelocidad();

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
    }

    public void draw(Graphics2D pincel){
        //System.out.println(v.getX() + " " + v.getY());
        BufferedImage image = v.getImage();
        pincel.drawImage(image, v.getScreenX(), v.getScreenY(), tablero.getCoordenada(1,1).length, tablero.getCoordenada(1,1).length, null);
    }

}
