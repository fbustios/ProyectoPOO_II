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

    LevelManager lm = new LevelManager(this);
    int nivelActual = 5;

    int gameState = 1;

    int FPS = 60;
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(15*lm.getTablero().getCoordenada(1,1).length, 13*lm.getTablero().getCoordenada(1,1).length));
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

        lm.setNivel(nivelActual);

            System.out.println("me metí al gameThread");

            lm.setVidaPerdida(false);
            lm.getHero().reposition();
        if(nivelActual%5 == 0) lm.setTiempo(30);
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
            lm.update(); // ya llama a los bichos

            double tiempo = lm.getTiempo() - (double) 1 / 60;
            lm.setTiempo(tiempo);

        }else if (gameState == 2) {
            mensajes.mostrarMensaje("Pausa");
        }
        if (lm.isPerder()) {
            mensajes.mostrarMensaje("Game Over");
            gameThread = null;
            return;
        }
        if(lm.isVidaPerdido()) {
            System.out.println("me metí al vida perdida");
            lm.resetTablero();
            startGameThread();
        }
        if(lm.isNivelCompletado()){
            System.out.println("Completó el nivel exitosamente, avanza de nivel");
            nivelActual++;
            lm.setNivelCompletado(false);
            lm.resetTablero();
            startGameThread();
        }
        //checkear aca estado del juego para ver si hay que empezar el loop con un nivel diferente o ya se perdió.
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D pincel = (Graphics2D) g;
        //imprimir las cosas en la pantalla
        lm.draw(pincel);
        if(lm.isPerder()){
            pincel.fillRect(0,0,48*15,48*13);
        }
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
