import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import static java.lang.Math.abs;

//Esta clase maneja la impresion en la pantalla
public class GamePanel extends JPanel implements Runnable, KeyListener {

    KeyHandler KH = new KeyHandler(this);
    Mensajes mensajes = new Mensajes(this);
    Sonido sonido = new Sonido();

    Tablero tab = new Tablero(this);
    Hero hero = Hero.getInstancia(tab,this,KH);

    int gameState = 1;

    int FPS = 60;
    Thread gameThread;

    DecimalFormat df = new DecimalFormat("0.0");
    double tiempo = 200;

    public GamePanel() {
        this.setPreferredSize(new Dimension(15*tab.getCoordenada(1,1).length, 13*tab.getCoordenada(1,1).length));
        this.setBackground(Color.YELLOW);
        this.setDoubleBuffered(true);
        this.addKeyListener(KH);
        this.setFocusable(true);
    }

    public void keyPressed(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){

    }

    public void keyTyped(KeyEvent e){

    }

    public void startGameThread(){

        tab.startLevel(hero,0);
        //esto hay que meterlo en start level
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            update();
            repaint();

            try{
                double remainingTime = abs(nextDrawTime - System.nanoTime());
                remainingTime /= 1000000;
                Thread.sleep((long)remainingTime);
                if(remainingTime <=0){
                    remainingTime = 0;
                }
                nextDrawTime += drawInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }


        }
        //manda a llamar update y paint, aqui manejamos cada cuanto se renueva pantalla
    }

    //
    public void update(){

        if (gameState == 1) {
            hero.update(); // ya llama a los bichos
            tiempo -= (double) 1 / 60;
        }else if (gameState == 2) {
            mensajes.mostrarMensaje("Pausa");
        }

        // pienso que cada coordenada debería manejar un x,y para que se pinte sola también, util para pintar muros cupones...
        //checkear aca estado del juego para ver si hay que empezar el loop con un nivel diferente o ya se perdió.
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D pincel = (Graphics2D) g;
        //imprimir las cosas en la pantalla
        tab.draw(pincel);
        hero.draw(pincel);
        mensajes.draw(pincel);
    }

    public void playMusic(int i){
        sonido.setFile(i);
        sonido.play();
        sonido.loop();
    }

    public void playSoundEffect(int i){
        sonido.setFile(i);
        sonido.play();
    }

    public void stopMusic(){
        sonido.stop();
    }
}

