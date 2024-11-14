import java.awt.*;
import java.text.DecimalFormat;

public class Mensajes {
    GamePanel panel;
    Font Consolas = new Font("Consolas", Font.BOLD, 30);
    String mensaje = "";
    Boolean messageOn = false;
    int messageCounter = 0;
    DecimalFormat df = new DecimalFormat("0.0");

    public Mensajes(GamePanel panel) {
        this.panel = panel;
    }

    public void draw(Graphics g2) {
        g2.setFont(Consolas);
        g2.setColor(Color.BLACK);

        g2.drawString("Nivel " + panel.lm.getLevel().getNumNivel(), 300, 30);
        g2.drawString("Vidas:" + panel.lm.getHero().getVidas(), 50, 30);
        g2.drawString("Score:", 250, 615);
        if(panel.lm.getTiempo()>0){
            g2.drawString("Tiempo:" + df.format(panel.lm.getTiempo()), 500, 30);}
        else{g2.drawString("Tiempo: 0,0" , 500, 30);}

        if (messageOn) {
            g2.drawString(mensaje, panel.getWidth() / 2 - 60, panel.getHeight() / 2 + 10);
            messageCounter++;

            if (messageCounter == 60) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
    public void mostrarMensaje(String mensaje) {
        this.mensaje = mensaje;
        messageOn = true;
    }
}
