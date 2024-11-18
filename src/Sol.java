public class Sol extends Coupon{
    static Sol instance = null;

    Sol(){
        this.nombre = "sol";
        this.spawnLevel = 1;
        this.AF = true;
        this.activo = false;
    }

    public void marcar(Coordenada a, boolean t){
        a.setCuponSol(t);
        a.setHayCupon(t);
        System.out.println("Entré a marcarrrrrrrrr");
        System.out.println(a.getX() + " " + a.getY());
    }

    public static Sol getInstance(){
    if(instance == null){
        instance = new Sol();
    }
    return instance;
    }
}
