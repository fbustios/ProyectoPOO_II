
public class Bomb {
    private int rango = 1;
    private int x;
    private int y;
    private boolean segunda = false;

    Bomb(){
    }
    public boolean verificar(Coordenada n){
        if(n==null || n.getMuroMetal()!=null) return false;
        return true;
    }

    public void explode(Tablero tab){
        Coordenada a = tab.getCoordenada(x,y);
        Puerta p = tab.getPuerta();
        a.setBomb(null);
        a.setHayFuego(1000);
        boolean left = true;
        boolean up = true;
        boolean right = true;
        boolean down = true;
        for(int i = 0; i <= rango; i++ ){
            Coordenada n = tab.getCoordenada(x+i,y);
            if(verificar(n) && down){
                if(n.getBomb() != null) {
                    n.getBomb().setSegunda(true);
                    n.getBomb().explode(tab);
                }
                n.setHayFuego(1000);
                if(p != null && ((n.getHayCupon()) && !n.getHayMuro() || n.getPuerta() !=null) && this.segunda){
                    p.setPenalizacion(true);
                }
            } else down = false;
            Coordenada n2 = tab.getCoordenada(x-i,y);
            if(verificar(n2) && up){
                if(n2.getBomb() != null){
                    n2.getBomb().setSegunda(true);
                    n2.getBomb().explode(tab);
                }
                n2.setHayFuego(1000);
                if(p != null && ((n2.getHayCupon()) && !n2.getHayMuro() || n2.getPuerta() !=null) && this.segunda){
                    p.setPenalizacion(true);
                }
            } else up = false;
            Coordenada n3 = tab.getCoordenada(x,y+i);
            if(verificar(n3) && right){
                if(n3.getBomb() != null){
                    n3.getBomb().setSegunda(true);
                    n3.getBomb().explode(tab);
                }
                if(p != null && ((n3.getHayCupon()) && !n3.getHayMuro() || n3.getPuerta() !=null) && this.segunda){
                    p.setPenalizacion(true);
                }
                n3.setHayFuego(1000);
            } else right = false;
            Coordenada n4 = tab.getCoordenada(x,y-i);
            if(verificar(n4) && left){
                if(n4.getBomb() != null){
                    n4.getBomb().setSegunda(true);
                    n4.getBomb().explode(tab);
                }
                if(p != null && ((n4.getHayCupon()) && !n4.getHayMuro() || n4.getPuerta() !=null) && this.segunda){
                    p.setPenalizacion(true);
                }
                n4.setHayFuego(1000);
            } else left = false;
        }
    }


    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setRango(int rango) {
        this.rango = rango;
    }
    public int getRango(){
        return rango;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setSegunda(boolean t){
        this.segunda = t;
    }
}
