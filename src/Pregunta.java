public class Pregunta extends Coupon{
    static Pregunta instance = null;

    Pregunta(){
        this.nombre = "pregunta";
        this.spawnLevel = 19;
        this.AF = false;
        this.activo = false;
    }

    @Override
    public void marcar(Coordenada a, boolean t) {
        a.setHayCupon(t);
        a.setCuponPregunta(t);
    }

    public static Pregunta getInstance(){
        if(instance == null){
            instance = new Pregunta();
        }
        return instance;
    }
}
