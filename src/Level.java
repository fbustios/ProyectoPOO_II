
import java.util.Random;

import static java.util.Collections.shuffle;

public class Level {
    private Tablero tab;
    private Hero hero;
    private int numNivel;
    private int cantVill;
    private double tiempo = 200;
    VillainPool pool;
    VillainPool poolMonG;

    Level(int i,Hero h, Tablero t, VillainPool p, VillainPool pMonG) {
        this.tab = t;
        this.hero = h;
        this.numNivel = i;
        this.pool = p;
        this.poolMonG = pMonG;
    }

    public void crearNivel(){
        System.out.println("crearNivel varias veces");
            if(numNivel%5 != 0){
                tab.setMurosMetal();
                tab.setMurosLadrillo();
                setCantVill();
            }
    }

    public void agregarVillano(){

        shuffle(pool.getNotInUse());
        Random rand = new Random();
        Villano v = null;
        for(int i = 0; i < pool.getNotInUse().size(); i++) {
            v = pool.obtain(i);
            if (!(v.getNivelInicial() <= numNivel)) {
                pool.release(v);
            } else break;
            System.out.println("llegué aqui 1");
        }
        if(cantVill != 0) {
            System.out.println("En proceso de colocar villanos ...");
                    boolean colocado = false;
                    while (!colocado) {
                        System.out.println("Colocando villanos ...");
                        int idx = rand.nextInt(2, 13);
                        int idx2 = rand.nextInt(2, 15);
                        Coordenada c = tab.getCoordenada(idx,idx2);
                        if (!c.getHayMuro()) {
                            v.setXY(idx,idx2);
                            v.setScreenXY(idx*48,idx2*48);
                            c.setVillano(v);
                            hero.attach(v);
                            cantVill--;
                            colocado = true;
                        }
                    }
        }
    }


    public void actualizarNivel(){
        if(tiempo%5==0 && tiempo!=0){
            System.out.println("me meti al if del tiempo");
            if(cantVill!=0){
                agregarVillano();
                System.out.println("me metí al del cantVillanos");
            }
        }
        else if(tiempo == 0){
            //hacer los enemigos monedas giratorias y agregar segun la cantidad de bichos del nivel
        }

         // se llama por cada pintada para ir agregando villanos o ver si ya acabó.
        if(cantVill==0){
            tab.setPuerta(true);
        }
    }

    public void setCantVill(){
        Random rand = new Random();
        if(numNivel < 5){
            cantVill = rand.nextInt(2,5);
        } else if(numNivel <= 10){
            cantVill = rand.nextInt(5,9);
        } else {
            cantVill = rand.nextInt(8,11);
        }
    }

    public int getNumNivel(){
        return numNivel;
    }
    public double getTiempo(){
        return tiempo;
    }

    public void setTiempo(double t){
        tiempo = t;
    }
}
