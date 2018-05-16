
package audio;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound implements Runnable{

    Clip clip;
    Thread thread;
    private boolean running = false;
    File so = new File("elevator.wav");


    public void run() {

        while(running) {
            PlaySound ( so );
        }
        stop();
    }

    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread ( this );
        thread.start ();
    }
    public synchronized void stop(){
        if(!running) return;
        running = false;
        clip.stop ();
    }

    public void PlaySound(File sound){

        try{
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream ( sound ));
            clip.start ();

            Thread.sleep(clip.getMicrosecondLength () / 1000);
        }catch (Exception e)
        {
            ;
        }

    }

}
