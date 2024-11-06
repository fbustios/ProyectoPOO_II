import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Math.abs;


public class GamePanel extends JPanel implements Runnable, KeyListener {

    //Se instancia lo que queremos que se vea en la pantalla como el jugador
    //Esta clase maneja la impresion en la pantalla
    KeyHandler KH = new KeyHandler();
    Tablero tab = new Tablero(this);
    Hero hero = Hero.getInstancia(tab,this,KH);
    //tab.setMurosLadrillo();
    //tab.setMurosMetal();

    int FPS = 60;
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(15*tab.getCoordenada(1,1).length, 13*tab.getCoordenada(1,1).length));
        this.setBackground(Color.GREEN);
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
        hero.update(); // ya llama a los bichos
        // pienso que cada coordenada debería manejar un x,y para que se pinte sola también, util para pintar muros cupones...
        //checkear aca estado del juego para ver si hay que empezar el loop con un nivel diferente o ya se perdió.
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D pincel = (Graphics2D) g;
        //imprimir las cosas en la pantalla
        tab.draw(pincel);
        hero.draw(pincel);

    }
}

