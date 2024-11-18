import java.util.ArrayList;
import java.util.List;

public class CouponPool {
    private List<String> notInUse = new ArrayList<>(List.of("Sol","BombaDorada", "Detonador", "Patín", "BombaRayada", "MuroRayado", "Pregunta", "HombreLlamas"));
    private List<String> inUse = new ArrayList<>();
    static CouponPool instance = new CouponPool();
    private CouponPool() {}

    public static CouponPool getInstance() {
        if (instance == null) {
            return instance = new CouponPool();
        }
        return instance;
    }


}
