public class Patin extends Coupon{
    static Patin instance = null;

    Patin(){
        this.nombre = "patin";
        this.spawnLevel = 4;
        this.AF = true;
        this.activo = false;
    }

    @Override
    public void marcar(Coordenada a, boolean t) {
    }

    public static Patin getInstance(){
        if(instance == null){
            instance = new Patin();
        }
        return instance;
    }
}
