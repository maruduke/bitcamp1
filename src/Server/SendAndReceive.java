package Server;

import OBJ.Player;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class SendAndReceive {
    List<Player> players; // 플레이어를 저장할 List<Player>
    String ThreadName = Thread.currentThread().getName(); // 실행중인 스레드의 이름을 저장

    public SendAndReceive(List<Player> players) {
        this.players = players;
    } // 플레이어 생성

    public void broadcast(String GameLog) throws IOException { // 플레이어의 게임 진행 로그 출력
        for (Player p : players) {
            Socket s = p.getSocket();

            OutputStream out = s.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out) );
            System.out.println(ThreadName + ": " + GameLog);
            pw.println(GameLog);
            pw.flush();
        }
    }

    public String turn(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        for (Player p : players) {
            Socket s = p.getSocket();
            OutputStream out = s.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out) );


            if(s.equals(socket)) {
                pw.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 당신의 턴입니다. >> 숫자를 입력하세요");
                pw.println("[   1. 평타   2. 강타   3. 공격력 버프   4. 회복   ]");
            }
            else {
                pw.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 상대방이 행동중입니다.");
            }
            pw.flush();

        }
        System.out.println("입력 대기 중");
        String command = br.readLine();
        System.out.println("입력 출력 중: " + command);
        return command;
    }



}

