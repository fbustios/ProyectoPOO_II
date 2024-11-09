import java.util.LinkedList;
import java.util.Queue;

public class BombPool {
    private static BombPool instancia = null;
    private int cantidad = 1;
    private Queue<Bomb> disponibles = new LinkedList<>();
    private Queue<Bomb> enUso = new LinkedList<>();

    //crea la cantidad de bombas que se pueden tener al mismo tiempo
    public BombPool(){
        for (int i = 0; i < cantidad; i++){
            disponibles.add(new Bomb());
        }
    }

    public Bomb obtenerBomba(){
        if(disponibles.isEmpty()){
            return null;
        }
        Bomb bomba = disponibles.poll();
        enUso.add(bomba);
        return bomba;
    }

    public void devolverBomba(Bomb bomba){
        enUso.remove(bomba);
        disponibles.add(bomba);
    }

    public void aplicarBombaDorada(){
        disponibles.add(new Bomb());
    }

    public void actualizarRango(int nuevoRango){
        for(Bomb bomba: disponibles){
            bomba.setRango(bomba.getRango()+nuevoRango);
        }
    }

    public Queue<Bomb> getEnUso(){
        return enUso;
    }

    static BombPool getBombPool(){
        if (instancia == null){
            instancia = new BombPool();
        }
        return instancia;
    }
}
