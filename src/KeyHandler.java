import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, spacePressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_9:
                shiftPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = false;
                break;
        }
    }
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_SHIFT:
                shiftPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                break;
        }

    }
    public void keyTyped(KeyEvent e) {

    }
}
