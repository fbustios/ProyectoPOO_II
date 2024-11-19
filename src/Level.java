
import java.util.Random;

import static java.util.Collections.shuffle;

public class Level {
    private Tablero tab;
    private Hero hero;
    private int numNivel;
    private int cantVill;
    private int copyVill;
    private int villanosRestantes;
    private boolean noVillainsLeft;
    private double tiempo = 200;
    private int infierno  = 0;
    private int spawnCounter = 0;
    private int spawnInterval = 300;
    private boolean puertaAbierta = false;
    private int cont = 0;
    VillainPool pool;
    VillainPool poolMonG;
    private static Random rand = new Random();
    private GamePanel panel;
    private boolean sonidoPuerta;
    private CouponManager CM = CouponManager.getInstance();
    private boolean ponerCupon;
    private Coupon coupon = null;

    Level(int i,Hero h, Tablero t, VillainPool p, VillainPool pMonG, GamePanel panel) {
        this.tab = t;
        this.hero = h;
        this.numNivel = i;
        this.pool = p;
        this.poolMonG = pMonG;
        this.noVillainsLeft = false;
        this.panel = panel;
        this.ponerCupon = true;
    }

    public void crearNivel(){
        System.out.println("Estoy creando un nivel");
        if(numNivel%5 == 0){hero.setInvencible(true);}
            tab.setMurosMetal();
            tab.setMurosLadrillo();
            tab.setPuerta(false);
            setCantVill();
            copyVill  = cantVill;
            sonidoPuerta = true;
            infierno = 0;


    }

    public void agregarVillano(){
        shuffle(pool.getNotInUse());
        Villano v = null;
        if(cantVill != 0) {
            for(int i = 0; i < pool.getNotInUse().size(); i++) {
                v = pool.obtain(i);
                if(infierno == 0){
                if (v.getNivelInicial() <= numNivel) {
                    break;
                } else {
                    pool.release(v);}
                } else {
                    if (v.getNivelInicial() == 14) {
                        break;
                    } else {
                        pool.release(v);}
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
        }
    }

    public void agregarCupon(){
        coupon = CM.obtainCoupon(numNivel);
        boolean colocado = false;
        if(!coupon.activo){
        while (!colocado) {
            int idx = rand.nextInt(2, 13);
            int idx2 = rand.nextInt(2, 15);
            Coordenada c = tab.getCoordenada(idx,idx2);
            System.out.println("estoy enciclado");
            if (!(c.getMuroLadrillo() == null) && (c.getPuerta() == null)) {
                System.out.println("Colocando cupon ...");
                coupon.marcar(c,true);
                colocado = true;
            }
        }}
    }

    public void explotarTesoroOculto(){
        numNivel++;
        while(villanosRestantes>0) {
            agregarVillano();
        }
        numNivel--;
    }


    public void actualizarNivel() {
        if(numNivel%5==0) {
            tab.setPuerta(false);
            hero.setInvencible(true);
            if (spawnCounter >= spawnInterval && cantVill != 0) {
                agregarVillano();
                cantVill--;
                spawnCounter = 0;
            } else {
                spawnCounter++;
            }
        } else {
            hero.setInvencible(false);
            while(cantVill != 0) {
                agregarVillano();
                cantVill--;
                cont++;
            }
        }
        if(ponerCupon){
            agregarCupon();
            ponerCupon = false;
        }
        if (pool.getInUse().isEmpty() && numNivel%5 != 0 && cont >= 2) {
            tab.setPuerta(true);
            if(sonidoPuerta){
            panel.playSoundEffect(4);
            sonidoPuerta = false;}
            this.puertaAbierta = true;
        }
        if(tab.getPuerta().getPenalizacion()){
            explotarTesoroOculto();
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

    public void agregarMonG(){
        cantVill = infierno;
        while(cantVill > 0){
            agregarVillano();
            cantVill--;
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
    public int getCopyVill(){
        return this.copyVill;
    }
    public void setInfierno(int t){
        this.infierno = t;
    }
}

