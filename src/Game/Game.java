package Game;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public interface Game {

    public void enterRoom(List<Socket> socket);
    public void start() throws IOException;

    boolean state();
}
