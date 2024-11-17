import java.awt.*;
public class LevelManager {
    private double tiempo;
    private boolean vidaPerdida = false;
    private Tablero tablero;
    private Hero hero;
    private boolean nivelCompletado = false;
    private int blackScreenCounter = 0;
    private boolean perder = false;
    private Level level = null;
    VillainPool pool;
    VillainPool poolMonG;
    int score = 0;


    LevelManager(GamePanel panel){
        this.tablero = new Tablero(panel);
        this.hero = Hero.getInstancia(tablero,panel,panel.KH);
        this.pool = VillainPool.getInstancia();
        pool.agregar(tablero);
    }


    public void setNivel(int i){
        System.out.println("Estoy haciendo un nivel ...");
        this.level = new Level(i, hero, tablero, pool,poolMonG);
        setTiempo(200);
        level.crearNivel();
    }

    public void draw(Graphics2D pincel) {

        //System.out.println("Estoy pintando lm ...");

        tablero.draw(pincel);
        hero.draw(pincel);

        if (vidaPerdida) {

            System.out.println("Estoy pintando la pantalla negra");
            drawBlackScreen(pincel);
            blackScreenCounter ++;

            if (blackScreenCounter == 120) {
                blackScreenCounter = 0;
                vidaPerdida = false;
            }
        }
    }

    public void update(){
        hero.update();
        level.actualizarNivel();
        if(!hero.isAlive() && hero.getVidas()!=0){
            vidaPerdida = true;
            while(!pool.getInUse().isEmpty()){
                hero.detach(pool.getInUse().getFirst());
                pool.release(pool.getInUse().getFirst());
                System.out.println("me guardé correctamente");
            }
        } else if(!hero.isAlive() && hero.getVidas()==0){
            System.out.println("Perdio del todo");
            this.perder = true;
        }
    }
    public void resetTablero(){
        tiempo = 200;
        tablero.vaciarTablero();
    }


    public void resetHero(){
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
}
