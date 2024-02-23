public class Main {
    public static void main(String[] args) {
          String dragonName = "Dragon";
                int dragonHP = 50;
                int dragonMaxHP = 100;
                int dragonMP = 70;
                int dragonMaxMP = 100;

                String player1Name = "Player1(Knight)";
                int player1HP = 10;
                int player1MaxHP = 30;
                int player1MP = 20;
                int player1MaxMP = 30;

                String player2Name = "Player2(Wizard)";
                int player2HP = 15;
                int player2MaxHP = 30;
                int player2MP = 15;
                int player2MaxMP = 30;

                System.out.println("+------------------------------+------+--------------------------------+");
                System.out.printf("     +--      HP %-2d / %-2d", dragonHP, dragonMaxHP);
                System.out.printf("       %-18s", dragonName);
                System.out.printf("MP %-2d / %-2d      --+\n", dragonMP, dragonMaxMP);
                System.out.println("+------------------------------+------+--------------------------------+");

                printPlayerInfo(player1Name, player1HP, player1MaxHP, player1MP, player1MaxMP);
                printPlayerInfo(player2Name, player2HP, player2MaxHP, player2MP, player2MaxMP);
                System.out.println("[1] 기본공격");
                System.out.println("[2] 스킬2");
                System.out.println("[3] 스킬2");
                System.out.println("[4] 스킬3");
                System.out.println("+----------------------------------------------------------------------+");
                System.out.println(">>  Player1의 턴입니다.");
            }

            private static void printPlayerInfo(String name, int hp, int maxHP, int mp, int maxMP) {
                System.out.printf("  %-30s\n", name);
                System.out.printf("  > HP %-2d / %-2d   MP %-2d / %-2d", hp, maxHP, mp, maxMP);
                System.out.println();
            }

    }

