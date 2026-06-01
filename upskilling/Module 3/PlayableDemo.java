interface Playable {
    void play();
}
class Guitar implements Playable {
    @Override
    public void play() {
        System.out.println("Playing the guitar: Strumming strings.");
    }
}
class Piano implements Playable {
    @Override
    public void play() {
        System.out.println("Playing the piano: Pressing keys.");
    }
}
public class PlayableDemo {
    public static void main(String[] args) {
        Playable guitar = new Guitar();
        Playable piano = new Piano(); 
        guitar.play();
        piano.play();
    }
}
