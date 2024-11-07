import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class RandomSystem implements VillainMovement {
    private boolean canMove = true;
    private String direction = "hola";
    private Villano v;
    private Tablero tablero;

    RandomSystem(Villano villano, Tablero tablero){
        this.v = villano;
        this.tablero = tablero;
    }

    public boolean move(int dx, int dy) {
        int X = v.getX();
        int Y = v.getY();
        Coordenada n = tablero.getCoordenada(X + dx, Y + dy);
        Coordenada a = tablero.getCoordenada(X, Y);
        if (n != null) {
            if (!n.getHayMuro()) {
                n.setVillano(v);
                a.setVillano(null);
                v.setXY(X+dx, Y+dy);
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
        int dx = rand.nextInt(-1,1); int s = rand.nextInt(0,1);
        int dy = 0;
        if(s==1){
            int temp = dx;
            dx = dy;
            dy = temp;
        }
        double velocidad = v.getVelocidad();
            if (dx == -1) {
                direction = "up";
                screenY -= velocidad;
                v.setScreenXY(screenX, screenY);

                if (screenY < x * 48 - 10) {
                    canMove = move(dx,dy);
                    if (!canMove) {
                        screenY += velocidad;
                        screenY += velocidad;
                        v.setScreenXY(screenX, screenY);
                    }
                }
            }
            if (dx==1) {
                direction = "down";
                screenY += velocidad;
                v.setScreenXY(screenX, screenY);
                if (screenY > (x + 1) * 48 - 45) {
                    System.out.println("Cambie de casilla abajo");
                    canMove = move(1,0);
                    if (!canMove) {
                        screenY -= velocidad;
                        v.setScreenXY(screenX, screenY);
                    }
                }
            }
            if (dy == -1) {
                direction = "left";
                screenX -= velocidad;
                v.setXY(screenX, screenY);
                if (screenX < y * 48 - 10) {
                    System.out.println("Cambie de casilla a la izquierda");
                    canMove = move(0,-1);
                    if (!canMove) {
                        screenX += velocidad;
                        v.setScreenXY(screenX, screenY);
                    }
                }
            }
            if (dy == 1) {
                direction = "right";
                screenX += velocidad;
                v.setScreenXY(screenX, screenY);
                if (screenX > (x + 1) * 48 - 40) {
                    System.out.println("Cambie de casilla a la derecha");
                    canMove = move(0,1);
                    if (!canMove) {
                        screenX -= velocidad;
                        v.setScreenXY(screenX, screenY);
                    }
                }
            }
    }

    public void draw(Graphics2D pincel){
        System.out.println(v.getX() + " " + v.getY());
        BufferedImage image = v.getImage();
        pincel.drawImage(image, v.getScreenX(), v.getScreenY(), tablero.getCoordenada(1,1).length, tablero.getCoordenada(1,1).length, null);
    }

};
