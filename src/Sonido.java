import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sonido {
    Clip clip;
    URL[] soundURL = new URL[5];

    public Sonido() {
        soundURL[0] = getClass().getResource("\\Sounds\\cinderella_clown.wav");
        soundURL[1] = getClass().getResource("\\Sounds\\explode.wav");
        soundURL[2] = getClass().getResource("\\Sounds\\mario_is_a_weenie.wav");
        soundURL[3] = getClass().getResource("\\Sounds\\putbomb.wav");
        soundURL[4] = getClass().getResource("\\Sounds\\winlevel.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("Error al cargar el sonido");
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}


