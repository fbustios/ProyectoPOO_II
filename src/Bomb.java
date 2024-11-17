
public class Bomb {
    private int rango = 1;
    private int x;
    private int y;

    Bomb(){
    }
    public boolean verificar(Coordenada n){
        if(n==null || n.getMuroMetal()!=null){
            return false;
        }

        return true;
    }

    public void explode(Tablero tab){
        Coordenada a = tab.getCoordenada(x,y);
        a.setHayFuego(1000);
        for(int i = 0; i <= rango; i++ ){
            Coordenada n = tab.getCoordenada(x+i,y);
            if(verificar(n)){
                n.setHayFuego(1000);
            }
            Coordenada n2 = tab.getCoordenada(x-i,y);
            if(verificar(n2)){
                n2.setHayFuego(1000);
            }
            Coordenada n3 = tab.getCoordenada(x,y+i);
            if(verificar(n3)){
                n3.setHayFuego(1000);
            }
            Coordenada n4 = tab.getCoordenada(x,y-i);
            if(verificar(n4)){
                n4.setHayFuego(1000);
            }
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
}
