package Game;

import java.net.Socket;

public interface Game {

    public void enterRoom(String name, Socket socket);
    public void waitGame();
    public void start();
}
