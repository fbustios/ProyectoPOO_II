import java.util.ArrayList;
import java.util.Random;

public class CouponManager {
    static CouponManager instance = null;
    private Sol sol = Sol.getInstance();
    private BombaDorada bombaDorada = BombaDorada.getInstance();
    private CouponDetonador detonador = CouponDetonador.getInstance();
    private Patin patin = Patin.getInstance();
    private BombaRayada bombaRayada = BombaRayada.getInstance();
    private MuroRayado muroRayado = MuroRayado.getInstance();
    private Pregunta pregunta = Pregunta.getInstance();
    private EnLlamas enLlamas = EnLlamas.getInstance();
    private ArrayList<Coupon> availableCoupons = new ArrayList<>();

    CouponManager(){
    availableCoupons.add(sol);
    availableCoupons.add(bombaDorada);
    availableCoupons.add(detonador);
    availableCoupons.add(bombaRayada);
    availableCoupons.add(muroRayado);
    availableCoupons.add(pregunta);
    availableCoupons.add(enLlamas);
     }

    public Coupon obtainCoupon(int level){
    if(level == 1){return sol;}
    if(level == 2){return bombaDorada;}
    if(level == 3){return detonador;}
    if(level == 4){return patin;}
    if(level == 11){return bombaRayada;}
    if(level == 14){return muroRayado;}
    if(level == 19){return pregunta;}
    if(level == 23){return enLlamas;}
    boolean chosen = false;
    Coupon coupon = null;

    while (!chosen) {
        Random rand = new Random();
        int num = rand.nextInt(0, 7);
        coupon = availableCoupons.get(num);
        if (coupon.getSpawnLevel() < level) {
            if (coupon.getAF() && coupon.getActivo()) {
            }
        } else {
            chosen = true;
        }
    }
    return coupon;
    }

    public static CouponManager getInstance(){
        if(instance == null){
            instance = new CouponManager();
        }
        return instance;
    }
}
