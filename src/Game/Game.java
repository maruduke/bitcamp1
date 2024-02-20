package Game;

import java.io.IOException;
import java.net.Socket;

public interface Game {

    public void enterRoom(Socket socket);
    public void start() throws IOException;

    boolean state();
}
