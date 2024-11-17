import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Espon extends Villano{
    private String direction = "Up";
    private int cont = 48;
    private String d = "Down";
    private boolean canMove = true;
    private int spriteCounter;
    private int spriteNumber;
    public Espon(Tablero tab, int nivelInicial,int velocidad,int puntaje, boolean atm){
        super(tab,nivelInicial,velocidad,atm,puntaje);
        try{
            image0 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Barril0.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("\\Villains\\Barril2.png"));
        } catch (Exception e){
            System.out.println("Error al leer el imagen");
            e.printStackTrace();

        }
    }

    @Override
    public void update(int x, int y, Graphics2D pincel) {
        moverVillano(x,y, pincel);
    }

    @Override
    public void moverVillano(int x, int y, Graphics2D pincel) {
        updateGraphics();
        draw(pincel);
    }
    public boolean move(int dx, int dy) {
        Coordenada n = tab.getCoordenada(x + dx,  y + dy);
        Coordenada a = tab.getCoordenada(x, y);
        if (n != null) {
            if (!n.getHayMuro() || (atm&& n.getMuroMetal()==null) && n.getBomb() == null) {
                n.setVillano(this);
                a.setVillano(null);
                x+=dx;
                y+=dy;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void updateGraphics() {
        Random rand = new Random();

        Coordenada a = tab.getCoordenada(x, y);
        if(a.getHayFuego()){
            vivo = false;
            a.setVillano(null);
            System.out.println("Me morí");
        }
        d = getDireccion();
        if(d == "Nada") {
            if (cont == 48) {
                int i = rand.nextInt(0, 4);
                if (i == 0) {
                    d = "Up";
                } else if (i == 1) {
                    d = "Down";
                } else if (i == 2) {
                    d = "Left";
                } else if (i == 3) {
                    d = "Right";
                }
                cont = 0;
            }
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
            if (screenX < y * 48 - 38) {
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

    }

    public void draw(Graphics2D pincel){
        System.out.println(x + " " + y);
        System.out.println(screenX + " " + screenY);

        if(spriteNumber==1){image = image0;}
        if(spriteNumber==2){image = image1;}

        if(active){
            System.out.println("Estoy activo");
            pincel.drawImage(image0, screenX, screenY, tab.getCoordenada(1,1).length, tab.getCoordenada(1,1).length, null);
        }
    }

    public void setScreenXY(int x, int y){
        this.screenX = x;
        this.screenY = y;
    }

    public String getDireccion(){
        d = "Nada";
        for(int i = x; i < 13; i++ ){
            if(tab.getCoordenada(i,y).getMuroMetal()!=null)break;
            d = "Down";
        }
        for(int j = y; j < 15; j++){
            if(tab.getCoordenada(x,j).getMuroMetal()!=null)break;
            d =  "Right";
        }
        for(int n = x; n > 0; n--){
            if(tab.getCoordenada(n,y).getMuroMetal()!=null)break;
            d = "Up";

        }
        for(int m = y; m > 0; m--){
            if(tab.getCoordenada(x,m).getMuroMetal()!=null)break;
            d = "Left";
        }
        return d;
    }


}
