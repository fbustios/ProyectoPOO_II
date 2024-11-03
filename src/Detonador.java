import java.util.LinkedList;
import java.util.Queue;

public class Detonador {
    private Queue<Bomb> bombas = new LinkedList<>();
    Detonador(){

    }

    public void addBomb(Bomb bomb){
        bombas.add(bomb);
    }

    public void explodeBomb(){
        Bomb expBomb = bombas.poll();
    }


}
