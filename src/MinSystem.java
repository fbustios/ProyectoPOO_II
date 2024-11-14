import javax.naming.NameClassPair;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class MinSystem implements VillainMovement {
    private boolean canMove = true;
    private String d = "N";
    private Villano v;
    private Tablero tablero;
    private int cont = 0;
    private boolean cambieCasilla = true;
    private int screenX;
    private int screenY;
    private List<String> movs;
    private boolean atm;
    private int spriteCounter = 0;
    private int spriteNumber = 1;

     MinSystem(Villano villano, Tablero tablero){
        this.v = villano;
        this.tablero = tablero;
        this.screenX = v.getScreenY();
        this.screenY = v.getScreenX();
        this.atm = v.getAtm();
        this.movs = null;
    }

    public boolean move(int dx, int dy) {
        Coordenada n = tablero.getCoordenada(v.getX() + dx,  v.getY() + dy);
        Coordenada a = tablero.getCoordenada(v.getX(), v.getY());
        if (n != null) {
            if (!n.getHayMuro() || (atm&&n.getMuroMetal()==null) && n.getBomb()==null) {
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

    public void updateGraphics(){
             movs = bfs();
         if(movs!=null){

             if(cambieCasilla) {
                 System.out.println("me meti 2");
                 if(movs.size()>0){
                 d = movs.get(0);
                 movs.remove(0);}
                 cambieCasilla = false;
             } else mover(d);
         }
         cont--;

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

    public void mover(String d) {
        int x = v.getX();
        int y = v.getY();

        double velocidad = v.getVelocidad();
        if (Objects.equals(d, "Up")) {
            screenY -= velocidad;

            if (screenY < x * 48 - 48) {
                canMove = move(-1,0);
                if (!canMove) {
                    screenY += velocidad;

                } else {
                    cambieCasilla = true;
                }
            }
        } else if (Objects.equals(d, "Down")) {
            screenY += velocidad;
            if (screenY > (x+1) * 48) {
                canMove = move(1,0);
                System.out.println("Cambie de casilla abajo");
                if (!canMove) {
                    screenY -= velocidad;
                } else {
                    cambieCasilla = true;
                }
            }
        } else if (Objects.equals(d, "Left")) {
            screenX -= velocidad;
            if (screenX < y * 48 - 48) {
                canMove = move(0,-1);
                System.out.println(canMove);
                if (!canMove) {
                    screenX += velocidad;
                } else {
                    cambieCasilla = true;
                }
            }
        } else if (Objects.equals(d, "Right")) {
            screenX += velocidad;
            if (screenX > (y+1) * 48) {
                canMove = move(0,1);
                System.out.println("Cambie de casilla a la derecha");
                if (!canMove) {
                    screenX -= velocidad;
                } else {
                    cambieCasilla = true;
                }
            }
        }
        v.setScreenXY(screenX, screenY);
    }
    public List<String> bfs(){
        Set<Coordenada> visitados = new HashSet<>();
        Queue<Pair<Coordenada, List<String>>> q = new LinkedList<>();
        Pair<Coordenada, List<String>> p = new Pair<>(tablero.getCoordenada(v.getX(), v.getY()), new ArrayList<>());
        q.add(p);
        List<String> camino = null;
        while(!q.isEmpty()){
            Pair<Coordenada, List<String>> act = q.poll();
            if(act.getFirst().hayHero()){
                return act.getSecond();
            }
            else if(q.isEmpty()){
                camino = act.getSecond();
            }
            int x = act.getFirst().getX();
            int y = act.getFirst().getY();
            Coordenada up = tablero.getCoordenada(x-1,y);
            if(up!=null && (!up.getHayMuro() || (v.getAtm()&& up.getMuroMetal()==null)) && !visitados.contains(up) && up.getBomb() == null){
                List<String> n = new ArrayList<>(act.getSecond());
                n.add("Up");
                visitados.add(up);
                q.add(new Pair<>(up,n));
            }
            Coordenada down = tablero.getCoordenada(x+1,y);
            if(down != null && (!down.getHayMuro() || (v.getAtm()&& down.getMuroMetal()==null)) && !visitados.contains(down) && down.getBomb()==null){
                List<String> n = new ArrayList<>(act.getSecond());
                n.add("Down");
                visitados.add(down);
                q.add(new Pair<>(down,n));
            }
            Coordenada left = tablero.getCoordenada(x,y-1);
            if(left!= null && (!left.getHayMuro() || (v.getAtm()&& left.getMuroMetal()==null)) && !visitados.contains(left) && left.getBomb()==null){
                List<String> n = new ArrayList<>(act.getSecond());
                n.add("Left");
                visitados.add(left);
                q.add(new Pair<>(left,n));
            }
            Coordenada right = tablero.getCoordenada(x,y+1);
            if(right!= null &&(!right.getHayMuro() || (v.getAtm()&& right.getMuroMetal()==null)) && !visitados.contains(right) && right.getBomb() == null){
                List<String> n = new ArrayList<>(act.getSecond());
                n.add("Right");
                visitados.add(right);
                q.add(new Pair<>(right,n));
            }
        }
        return camino;
    }

    public void draw(Graphics2D pincel){
        System.out.println(v.getX() + " " + v.getY());
        System.out.println(screenX + " " + screenY);

        BufferedImage image = null;

        if(spriteNumber==1){image = v.getImage0();}
        if(spriteNumber==2){image = v.getImage1();}

        cont--;
        pincel.drawImage(image, v.getScreenX(), v.getScreenY(), tablero.getCoordenada(1,1).length, tablero.getCoordenada(1,1).length, null);
    }
    public void setScreenXY(int x, int y){
         this.screenX = x;
         this.screenY = y;
    }

}
