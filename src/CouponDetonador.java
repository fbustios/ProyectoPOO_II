public class CouponDetonador extends Coupon{
    static CouponDetonador instance = null;

    CouponDetonador(){
        this.nombre = "detonador";
        this.spawnLevel = 3;
        this.AF = false;
        this.activo = false;
    }

    @Override
    public void marcar(Coordenada a, boolean t) {
        a.setHayCupon(t);
        a.setHayDetonador(t);
    }

    public static CouponDetonador getInstance(){
        if(instance == null){
            instance = new CouponDetonador();
        }
        return instance;
    }
}
