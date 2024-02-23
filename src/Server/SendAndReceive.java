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
            Socket s = p.getSocket(); // 플레이어의 소켓 저장

            OutputStream out = s.getOutputStream(); // 출력스트림 생성
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out)); // 문자로 출력하기 위한 출력스트림 생성
            System.out.println(ThreadName + ": \n" + GameLog); // 서버에 출력
            pw.println(GameLog); // 클라이언트에 출력
            pw.flush();
        }
    }

    public String turn(Socket socket) throws Exception { // 플레이어의 턴
        InputStream in = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        for (Player p : players) {
            Socket s = p.getSocket(); // 플레이어의 소켓 얻어오기
            OutputStream out = s.getOutputStream(); // 아웃스트림 생성
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out) ); // 스트림 출력

            if(s.equals(socket)) { // 플레이어의 소켓이 일치하면 턴이 시작됨
                pw.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 당신의 턴입니다. >> 숫자를 입력하세요");
                pw.println("[   1. 평타   2. 강타   3. 공격력 버프   4. 회복   ]");
                Thread.sleep(1000);
            }
            else {
                pw.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 상대방이 행동중입니다.");
                Thread.sleep(1000);
            }
            pw.flush();
        }
        System.out.println("입력 대기 중");
        String command = null;
        while(command == null) {
            command = br.readLine();
        }
        System.out.println("입력 출력 중" + command);
        return command;
    }
}

