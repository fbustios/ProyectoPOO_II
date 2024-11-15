import java.util.ArrayList;

public class VillainPool {
    private ArrayList<Villano> inUse = new ArrayList<>();
    private ArrayList<Villano> notInUse = new ArrayList<>();
    private static VillainPool instancia = null;//hacerlo singleton para tenerlo en cada villano y regresarse cuando se muera

    private VillainPool(){
    }

    public void agregar(Tablero tab){
        Globo g = new Globo(tab,1,1,100,false);
        Globo g2 = new Globo(tab,1,1,100,false);
        notInUse.add(g);
        notInUse.add(g2);
        MonG moneda = new MonG(tab,15,1,4000,true);
        MonG moneda2 = new MonG(tab,15,1,4000,true);
        //notInUse.add(moneda);
        //notInUse.add(moneda2);
    }

    public ArrayList<Villano> getNotInUse(){
        return notInUse;
    }
    public ArrayList<Villano> getInUse(){
        return inUse;
    }

    public Villano obtain(int i){
        if(!notInUse.isEmpty()){
            Villano v = notInUse.get(i);
            notInUse.remove(i);
            inUse.add(v);
            return v;
        }
        return null;
    }
    public void release(Villano v){
        inUse.remove(v);
        v.reset();
        notInUse.add(v);
    }
    public static VillainPool getInstancia(){
        if(instancia == null){
            instancia = new VillainPool();
            return instancia;
        }
        return instancia;
    }
}
