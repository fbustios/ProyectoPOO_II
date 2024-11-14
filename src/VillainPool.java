import java.util.ArrayList;

public class VillainPool {
    private ArrayList<Villano> inUse = new ArrayList<>();
    private ArrayList<Villano> notInUse = new ArrayList<>();

    VillainPool(Tablero tab){
        Globo g = new Globo(tab,1,1,100,false);
        notInUse.add(g);
        notInUse.add(g);
        MonG moneda = new MonG(tab,15,1,4000,true);
        notInUse.add(moneda);
        notInUse.add(moneda);
    }

    public ArrayList<Villano> getNotInUse(){
        return notInUse;
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
        v.setVivo(true);
        v.setXY(0,0);
        v.setScreenXY(0,0);
        notInUse.add(v);
    }
}
