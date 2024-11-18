import java.util.ArrayList;

public class VillainPool {
    private ArrayList<Villano> inUse = new ArrayList<>();
    private ArrayList<Villano> notInUse = new ArrayList<>();
    private static VillainPool instancia = null;//hacerlo singleton para tenerlo en cada villano y regresarse cuando se muera

    private VillainPool(){
    }

    public void agregar(Tablero tab){
        for(int i = 0; i < 10; i++){
            notInUse.add(new Globo(tab,1,1,100,false));
            notInUse.add(new Mon(tab,11,1,3000,false));
            notInUse.add(new MonG(tab,14,1,4000,true,false));
            notInUse.add(new Espon(tab,6,1,1000,true));
            notInUse.add(new Haki(tab,3,1,400,false));
            notInUse.add(new Fant(tab,8,1,2000,true));

        }
        for(int i = 0; i < 16; i++){
            notInUse.add(new MonG(tab,14,1,3000,true,true));

        }
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
            v.active = true;
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
