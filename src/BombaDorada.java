public class BombaDorada extends Coupon{
    static BombaDorada instance = null;

    BombaDorada(){
        this.nombre = "bombaDorada";
        this.spawnLevel = 2;
        this.AF = true;
        this.activo = false;
    }

    @Override
    public void marcar(Coordenada a, boolean t) {
        a.setCuponBombaDorada(t);
        a.setHayCupon(t);
    }

    public static BombaDorada getInstance(){
        if(instance == null){
            instance = new BombaDorada();
        }
        return instance;
    }
}
