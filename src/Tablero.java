import java.awt.*;
import java.util.Random;



public class Tablero {
    Coordenada[][] tablero;
    private int nivelActual;
    private GamePanel panel;
    private Puerta puerta = null;

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
        int cant = rand.nextInt(5,(tablero.length * tablero[0].length))/6;
        boolean ponerPuerta = true;
        int colocados = 0;
        while(colocados<=cant){
            int idx = rand.nextInt(2, tablero.length);
            int idx2 = rand.nextInt(2, tablero[0].length);
            if(!tablero[idx][idx2].getHayMuro()){
                if(ponerPuerta){
                    this.puerta = new Puerta(idx,idx2);
                    tablero[idx][idx2].setPuerta(puerta);
                    ponerPuerta = false;
                }
                tablero[idx][idx2].setMuroLadrillo();
                colocados ++;
            }
        }
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

    public void vaciarTablero(){
        for(int i = 0; i<13; i++){
            for(int j = 0; j<15; j++){
                tablero[i][j] = new Coordenada(i,j);
            }
        }
    }
    public int getPuertaX(){
       return puerta.getX();
    }
    public int getPuertaY(){
        return puerta.getY();
    }

    public void setPuerta(boolean estado){
        puerta.setAbierta(estado);
    }
    public Puerta getPuerta(){
        return puerta;
    }
}
