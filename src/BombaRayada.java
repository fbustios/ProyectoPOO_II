public class BombaRayada extends Coupon{
    static BombaRayada instance = null;

    BombaRayada(){
        this.nombre = "bombaRayada";
        this.spawnLevel = 11;
        this.AF = false;
        this.activo = false;
    }

    @Override
    public void marcar(Coordenada a, boolean t) {
        a.setCuponBombaRayado(t);
        a.setHayCupon(t);
    }

    public static BombaRayada getInstance(){
        if(instance == null){
            instance = new BombaRayada();
        }
        return instance;
    }
}
