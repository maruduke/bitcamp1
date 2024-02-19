package Game;

import java.net.Socket;

public interface Game {

    public void enterRoom(Socket socket);
    public void start();
}
