import java.awt.*;

public class Mensajes {
    GamePanel gp;
    Font Consolas = new Font("Consolas", Font.BOLD, 30);
    String mensaje = "";
    Boolean messageOn = false;
    int messageCounter = 0;

    public Mensajes(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics g2) {
        g2.setFont(Consolas);
        g2.setColor(Color.BLACK);

        if(gp.tiempo>0){
            g2.drawString("Tiempo:" + gp.df.format(gp.tiempo), 500, 30);}
        else{g2.drawString("Tiempo: 0,0" , 500, 30);}

        if (messageOn) {
            g2.drawString(mensaje, gp.getWidth() / 2 - 60, gp.getHeight() / 2 + 10);
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
