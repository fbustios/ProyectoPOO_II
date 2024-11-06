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
        Random rand = new Random();
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
        int X = v.getX();
        int Y = v.getY();
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
                Y -= velocidad;
                v.setXY(X, Y);

                if (Y < X * 48 - 10) {
                    System.out.println("Cambie de casilla arriba");
                    canMove = move(dx,dy);
                    if (!canMove) {
                        Y += velocidad;
                        Y += velocidad;
                        v.setXY(X, Y);
                    }
                }
            } else if (dx==1) {
                direction = "down";
                Y += velocidad;
                v.setXY(X, Y);
                if (Y > (X + 1) * 48 - 45) {
                    System.out.println("Cambie de casilla abajo");
                    canMove = move(1,0);
                    if (!canMove) {
                        Y -= velocidad;
                        v.setXY(X, Y);
                    }
                }
            } else if (dy == -1) {
                direction = "left";
                X -= velocidad;
                v.setXY(X, Y);
                if (X < Y * 48 - 10) {
                    System.out.println("Cambie de casilla a la izquierda");
                    canMove = move(0,-1);
                    if (!canMove) {
                        X += velocidad;
                        v.setXY(X, Y);
                    }
                }
            } else if (dy == 1) {
                direction = "right";
                X += velocidad;
                v.setXY(X, Y);
                if (X > (Y + 1) * 48 - 40) {
                    System.out.println("Cambie de casilla a la derecha");
                    canMove = move(0,1);
                    if (!canMove) {
                        X -= velocidad;
                        v.setXY(X, Y);
                    }
                }
            }
    }

    public void draw(Graphics2D pincel){
        BufferedImage image = null;
        pincel.drawImage(image, v.getX(), v.getY(), tablero.getCoordenada(1,1).length, tablero.getCoordenada(1,1).length, null);
    }

};
