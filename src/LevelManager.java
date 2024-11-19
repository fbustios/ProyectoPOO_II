import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class LevelManager {
    private double tiempo;
    private boolean vidaPerdida = false;
    private boolean vidaPerdida2 = false;
    private Tablero tablero;
    private Hero hero;
    private boolean nivelCompletado = false;
    private int blackScreenCounter = 0;
    private boolean perder = false;
    private boolean infierno  = false;
    private Level level = null;
    private VillainPool pool;
    private VillainPool poolMonG;
    private GamePanel panel;


    LevelManager(GamePanel panel){
        this.tablero = new Tablero(panel);
        this.hero = Hero.getInstancia(tablero,panel,panel.KH);
        this.pool = VillainPool.getInstancia();
        pool.agregar(tablero);
        this.panel = panel;
    }


    public void setNivel(int i){
        System.out.println("Estoy haciendo un nivel ...");
        this.level = new Level(i, hero, tablero, pool,poolMonG, panel);
        if(i%5==0) setTiempo(30);
        else setTiempo(200);
        this.infierno = false;
        level.crearNivel();
    }

    public void draw(Graphics2D pincel) {

        //System.out.println("Estoy pintando lm ...");

        tablero.draw(pincel);
        hero.draw(pincel);

        if (vidaPerdida2) {
            System.out.println("Estoy pintando la pantalla negra");
            drawBlackScreen(pincel);
            blackScreenCounter ++;

            if (blackScreenCounter == 60) {
                blackScreenCounter = 0;
                vidaPerdida2 = false;
            }
        }
    }

    public void update(){
        hero.update();
        level.actualizarNivel();
        if(level.isPuertaAbierta() && tablero.getPuertaX() == hero.getX() && tablero.getPuertaY() == hero.getY()){
            nivelCompletado = true;
            hero.reposition();
            if(!pool.getInUse().isEmpty()) sacarVillanos();
            System.out.println("Nivel completado");
        }
        if(!hero.isAlive() && hero.getVidas()!=0){
            vidaPerdida = true;
            vidaPerdida2 = true;
            sacarVillanos();
        }
        if(!hero.isAlive() && hero.getVidas()==0){
            System.out.println("Perdio del todo");
            this.perder = true;
        }
        if(tiempo<0 && !pool.getInUse().isEmpty() && !infierno && level.getNumNivel()%5!=0){
            System.out.println("Esta es la cantidad de villanos original: " + level.getCopyVill());
            int cant = pool.getInUse().size() + level.getCopyVill();
            System.out.println("Esta es la cantidad de monedas a poner: " + cant);
            infierno = true;
            level.setInfierno(cant);
            sacarVillanos();
            tablero.resetVillanos();
            level.agregarMonG();
        }
        if(tiempo < 0 && level.getNumNivel()%5 == 0){
            nivelCompletado = true;
            hero.reposition();
            sacarVillanos();
            System.out.println("Nivel completado");
        }
    }
    public void resetTablero(){
        tiempo = 200;
        tablero.vaciarTablero();
    }

    public void sacarVillanos(){
        while(!pool.getInUse().isEmpty()){
            hero.detach(pool.getInUse().getFirst());
            if(pool.getInUse().getFirst() instanceof MonG || pool.getInUse().getFirst() instanceof Mon) {
                pool.getInUse().getFirst().setIa(new MinSystem(pool.getInUse().getFirst(), tablero));
            }
            pool.release(pool.getInUse().getFirst());

            System.out.println("me guardé correctamente");
        }
    }

    public boolean isNivelCompletado(){
        return nivelCompletado;
    }
    public boolean isVidaPerdido(){
        return vidaPerdida;
    }
    public void setVidaPerdida(boolean vidaPerdida){
        this.vidaPerdida = vidaPerdida;
    }

    public void drawBlackScreen(Graphics2D pincel){
        pincel.setColor(Color.darkGray);
        pincel.fillRect(0,0,48*15,48*13);
    }

    public double getTiempo(){return tiempo;}

    public void setTiempo(double tiempo){
        this.tiempo = tiempo;
        level.setTiempo(tiempo);
    }

    public Level getLevel() {return level;}

    public Hero getHero() {return hero;}

    public Tablero getTablero() {return tablero;}

    public boolean isPerder() {return perder;}

    public void setNivelCompletado(boolean t){
        this.nivelCompletado = t;
    }
}
