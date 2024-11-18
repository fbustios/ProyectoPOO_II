public class EnLlamas extends Coupon{
    static EnLlamas instance = null;

    EnLlamas(){
        this.nombre = "hombreEnLlamas";
        this.spawnLevel = 23;
        this.AF = false;
        this.activo = false;
    }

    @Override
    public void marcar(Coordenada a, boolean t) {
        a.setHayCupon(t);
        a.setCuponHombreLlamas(t);
    }

    public static EnLlamas getInstance(){
        if(instance == null){
            instance = new EnLlamas();
        }
        return instance;
    }
}