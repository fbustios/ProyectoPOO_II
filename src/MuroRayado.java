public class MuroRayado extends Coupon{
    static MuroRayado instance = null;

    MuroRayado(){
        this.nombre = "muroRayado";
        this.spawnLevel = 14;
        this.AF = false;
        this.activo = false;
    }

    @Override
    public void marcar(Coordenada a, boolean t) {
        a.setCuponMuroRayado(t);
        a.setHayCupon(t);
    }

    public static MuroRayado getInstance(){
        if(instance == null){
            instance = new MuroRayado();
        }
        return instance;
    }
}
