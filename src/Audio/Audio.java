package Audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Audio {


    static Media clickMedia = new Media(Audio.class.getResource("Sounds/click.wav").toExternalForm());
    static Media menuSound = new Media(Audio.class.getResource("Sounds/Menu.mp3").toExternalForm());

    public static void click(){
        MediaPlayer clickPlayer = new MediaPlayer(clickMedia);
        clickPlayer.play();

    }

    public static void theme(){

        MediaPlayer musicPlayer = new MediaPlayer(menuSound);
        musicPlayer.setOnEndOfMedia(() -> musicPlayer.seek(Duration.ZERO));
        musicPlayer.setOnReady(musicPlayer::play);

        musicPlayer.play();
    }
}
