import java.util.LinkedList;
import java.util.Queue;

public class Detonador {
    private Queue<Bomb> bombas = new LinkedList<>();
    private BombPool piscina = BombPool.getBombPool();

    Detonador(){
    }

    public void addBomb(Bomb bomb){
        bombas.add(bomb);
    }

    public void explodeBomb(Tablero tab, GamePanel panel){
        Bomb expBomb = bombas.poll();
        if(expBomb!=null) {
            int x = expBomb.getX();
            int y = expBomb.getY();
            expBomb.explode(tab);
            panel.playSoundEffect(1);
            tab.getCoordenada(x,y).setBomb(null);
            piscina.devolverBomba(expBomb);
        }
    }


}
