
import java.util.Random;

import static java.util.Collections.shuffle;

public class Level {
    private Tablero tab;
    private Hero hero;
    private int numNivel;
    private int cantVill;
    private int villanosRestantes;
    private boolean noVillainsLeft;
    private double tiempo = 200;
    private int spawnCounter = 0;
    private int spawnInterval = 300;
    private boolean puertaAbierta = false;
    VillainPool pool;
    VillainPool poolMonG;
    private static Random rand = new Random();

    Level(int i,Hero h, Tablero t, VillainPool p, VillainPool pMonG) {
        this.tab = t;
        this.hero = h;
        this.numNivel = i;
        this.pool = p;
        this.poolMonG = pMonG;
        this.noVillainsLeft = false;
    }

    public void crearNivel(){
        System.out.println("Estoy creando un nivel");
        if(numNivel%5 != 0){
            tab.setMurosMetal();
            tab.setMurosLadrillo();
            tab.setPuerta(false);
            setCantVill();
        }
    }

    public void agregarVillano(){
        shuffle(pool.getNotInUse());

        Villano v = null;
        if(cantVill != 0) {
            for(int i = 0; i < pool.getNotInUse().size(); i++) {
                v = pool.obtain(i);

                if (v.getNivelInicial() <= numNivel) {
                    break;
                } else {
                    pool.release(v);
                }

                System.out.println("llegué aquí 1");
            }
        }
        if(cantVill != 0 && v!=null) {
            //System.out.println("En proceso de colocar villanos ...");
            boolean colocado = false;
            while (!colocado) {
                System.out.println("Colocando villanos ...");
                int idx = rand.nextInt(2, 13);
                int idx2 = rand.nextInt(2, 15);
                Coordenada c = tab.getCoordenada(idx,idx2);
                if (!c.getHayMuro()) {
                    v.setXY(idx,idx2);
                    v.setScreenXY(idx2*48,idx*48);
                    c.setVillano(v);
                    hero.attach(v);
                    colocado = true;
                }
            }
            cantVill--;
        }
    }


    public void actualizarNivel() {
        if (spawnCounter >= spawnInterval && cantVill != 0) {
            agregarVillano();
            spawnCounter = 0;
        } else {
            spawnCounter++;
        }
        if(cantVill==0) noVillainsLeft = true;
        if (pool.getInUse().isEmpty() && noVillainsLeft) {
            tab.setPuerta(true);
            this.puertaAbierta = true;
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

    public int getVillanosRestantes() {
        return villanosRestantes;
    }

    public boolean isPuertaAbierta(){
        return puertaAbierta;
    }
}

