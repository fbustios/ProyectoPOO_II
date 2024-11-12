import javax.naming.NameClassPair;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class MinSystem implements VillainMovement {
    private boolean canMove = true;
    private String d = "Down";
    private Villano v;
    private Tablero tablero;
    private int cont = 30;
    private boolean cambieCasilla = false;
    private int screenX;
    private int screenY;
    private List<String> movs;
    private boolean atm;

     MinSystem(Villano villano, Tablero tablero){
        this.v = villano;
        this.tablero = tablero;
        this.screenX = v.getScreenY();
        this.screenY = v.getScreenX();
        this.atm = v.getAtm();
        this.movs = bfs();
    }

    public boolean move(int dx, int dy) {
        Coordenada n = tablero.getCoordenada(v.getX() + dx,  v.getY() + dy);
        Coordenada a = tablero.getCoordenada(v.getX(), v.getY());
        if (n != null) {
            if (!n.getHayMuro() || (atm&&n.getMuroMetal()==null)) {
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
        if(cont==0){
            movs = bfs();
            cont=30;
        }
         if(movs!=null) {
             for(String i: movs) {
                 System.out.println(i);
             }
             System.out.println("Termino");
             while (!movs.isEmpty()) {
                 String d = movs.get(0);
                 movs.remove(0);
                 mover(d);
             }
         }
    }

    public void mover(String d) {
        int x = v.getX();
        int y = v.getY();

        double velocidad = v.getVelocidad();
        if (Objects.equals(d, "Up")) {
            screenY -= velocidad;

            if (screenY < x * 48 - 10) {
                canMove = move(-1,0);
                if (!canMove) {
                    screenY += velocidad;

                } else {
                    cambieCasilla = true;
                }
            }
        } else if (Objects.equals(d, "Down")) {
            screenY += velocidad;
            if (screenY > (x + 1) * 48 - 55) {
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
            if (screenX < y * 48 - 10) {
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
            if (screenX > (y + 1) * 48 - 40) {
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
        while(!q.isEmpty()){
            Pair<Coordenada, List<String>> act = q.poll();
            if(act.getFirst().hayHero()){
                return act.getSecond();
            }
            int x = act.getFirst().getX();
            int y = act.getFirst().getY();
            Coordenada up = tablero.getCoordenada(x-1,y);
            if(up!=null && (!up.getHayMuro() || (v.getAtm()&& up.getMuroMetal()==null)) && !visitados.contains(up)){
                List<String> n = new ArrayList<>(act.getSecond());
                n.add("Up");
                visitados.add(up);
                q.add(new Pair<>(up,n));
            }
            Coordenada down = tablero.getCoordenada(x+1,y);
            if(down != null && (!down.getHayMuro() || (v.getAtm()&& down.getMuroMetal()==null)) && !visitados.contains(down)){
                List<String> n = new ArrayList<>(act.getSecond());
                n.add("Down");
                visitados.add(down);
                q.add(new Pair<>(down,n));
            }
            Coordenada left = tablero.getCoordenada(x,y-1);
            if(left!= null && (!left.getHayMuro() || (v.getAtm()&& left.getMuroMetal()==null)) && !visitados.contains(left)){
                List<String> n = new ArrayList<>(act.getSecond());
                n.add("Left");
                visitados.add(left);
                q.add(new Pair<>(left,n));
            }
            Coordenada right = tablero.getCoordenada(x,y+1);
            if(right!= null &&(!right.getHayMuro() || (v.getAtm()&& right.getMuroMetal()==null)) && !visitados.contains(right)){
                List<String> n = new ArrayList<>(act.getSecond());
                n.add("Right");
                visitados.add(right);
                q.add(new Pair<>(right,n));
            }
        }
        return null;
    }

    public void draw(Graphics2D pincel){
        //System.out.println(v.getX() + " " + v.getY());
        //System.out.println(screenX + " " + screenY);
        BufferedImage image = v.getImage();
        cont--;
        pincel.drawImage(image, v.getScreenX(), v.getScreenY(), tablero.getCoordenada(1,1).length, tablero.getCoordenada(1,1).length, null);
    }

}
