import java.awt.*;
import java.util.Random;



public class Tablero {
    Coordenada[][] tablero;
    private int nivelActual;
    private GamePanel panel;
    public Tablero(GamePanel panel){
        tablero = new Coordenada[13][15];  //por mientras
        for(int i = 0; i<13; i++){
            for(int j = 0; j<15; j++){
                tablero[i][j] = new Coordenada(i,j);
            }
        }
        panel.playMusic(2);
        this.panel = panel;
        nivelActual = 0;
    }

    public void setMurosMetal(){
        for(int i = 1; i < tablero.length; i+=2){
            for(int j = 1; j <tablero[i].length; j+=2){
                tablero[i][j].setMuroMetal();
            }
        }

    }

    public void setMurosLadrillo(){
        Random rand = new Random();
        int cant = rand.nextInt((tablero.length * tablero[0].length));
        boolean ponerPuerta = true;
        for(int i = 0; i < cant; i+=2){
            int idx = rand.nextInt(2, tablero.length);
            int idx2 = rand.nextInt(2, tablero[0].length);

            if(!tablero[idx][idx2].getHayMuro()){
                if(ponerPuerta){
                    tablero[idx][idx2].setPuerta(new Puerta());
                    ponerPuerta = false;
                }
                tablero[idx][idx2].setMuroLadrillo();
            }
        }
    }

    public void startLevel(Hero hero, int nivel){
        setMurosMetal();
        setMurosLadrillo();
        tablero[0][0].setHero(hero);
        this.nivelActual = nivel;
        hero.attach(new Globo(6,6,this,1,1,100,true));
        hero.attach(new MonG(10,10,this,1,1,500,true));
        hero.attach(new Globo(4,5,this,1,1,100,true));
        hero.attach(new Globo(10,11,this,1,1,100,true));
        hero.attach(new Globo(8,4,this,1,1,100,true));
    }

    public Coordenada getCoordenada(int x, int y){
        try{
            return tablero[x][y];
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }
    public void draw(Graphics2D pincel){
        for(int i = 0; i < 13 ; i++){
            for(int j = 0; j < 15; j++){
                tablero[i][j].draw(pincel);
            }
        }
    }

    public void printTablero(){
        for(int i = 0; i < 13 ; i++){
            System.out.println();
            for(int j = 0; j < 15; j++){
                if(tablero[i][j].getHayMuro()){
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
        }
    }
}
