import java.awt.*;

public class LevelManager {
    private double tiempo;
    private boolean vidaPerdida = false;
    private Tablero tablero;
    private Hero hero;
    private boolean nivelCompletado = false;
    private int blackScreenCounter = 0;
    private Level level = null;
    VillainPool pool;
    VillainPool poolMonG;


    LevelManager(GamePanel panel){
        this.tablero = new Tablero(panel);
        this.hero = Hero.getInstancia(tablero,panel,panel.KH);
        this.pool = new VillainPool(tablero);
    }


    public void setNivel(int i){
        System.out.println("Estoy haciendo un nivel ...");
        this.level = new Level(i, hero, tablero, pool,poolMonG);
        this.tiempo = level.getTiempo();
        level.crearNivel();
    }

    public void draw(Graphics2D pincel){

        //System.out.println("Estoy pintando lm ...");

        tablero.draw(pincel);
        hero.draw(pincel);

        if(vidaPerdida){
            drawBlackScreen(pincel);
            blackScreenCounter ++;

            if (blackScreenCounter == 180) {
                blackScreenCounter = 0;
                vidaPerdida = false;
            }
        }
    }

    public void update(){
        hero.update();
        level.actualizarNivel();
        if(!hero.isAlive() && hero.getVidas()!=0){
            resetTablero(); vidaPerdida = true;
        } else if(hero.isAlive() && hero.getVidas()==0){
            System.out.println("Perdio del todo");
        }
    }
    public void resetTablero(){
        tiempo = 200;
        tablero.vaciarTablero();
        level.crearNivel();
    }

    public void resetHero(){
    }

    public boolean isNivelCompletado(){
        return nivelCompletado;
    }
    public boolean isVidaPerdido(){
        return vidaPerdida;
    }

    public void drawBlackScreen(Graphics2D pincel){
        pincel.drawRect(0,0,48*13,48*15);
    }

    public double getTiempo(){return tiempo;}

    public void setTiempo(double tiempo){
        this.tiempo = tiempo;
        level.setTiempo(tiempo);
    }

    public Level getLevel() {return level;}

    public Hero getHero() {return hero;}

    public Tablero getTablero() {return tablero;}
}
