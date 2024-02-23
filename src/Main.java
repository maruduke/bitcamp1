public class Main {
    public static void main(String[] args) {
        String player1Name = "Player1(Knight)";
        int player1HP = 10;
        int player1MaxHP = 30;
        int player1MP = 20;
        int player1MaxMP = 30;

        String dragonName = "Dragon";
        int dragonHP = 50;
        int dragonMaxHP = 100;
        int dragonMP = 70;
        int dragonMaxMP = 100;

        System.out.println("+------------------------------+------+--------------------------------+");
        System.out.printf("|    +--      HP %-2d / %-2d", dragonHP, dragonMaxHP);
        System.out.printf("               %-18s", dragonName);
        System.out.printf("MP %-2d / %-2d      --+          |\n", dragonMP, dragonMaxMP);
        System.out.println("+------------------------------+------+--------------------------------+");
        System.out.println(">>  Player1의 턴입니다.");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.printf("|  > %-30s : %-6s : [1] 기본공격                                        |\n",
            player1Name, "");
        System.out.printf("|  HP %-2d / %-2d   MP %-2d / %-2d      : %-6s : [2] 스킬aaa                 |\n",
            player1HP, player1MaxHP, player1MP, player1MaxMP, "");
        System.out.printf("|  %-30s : %-6s : %-6s : [3] 스킬2              |\n", "Player2(Wizard)", "", "");
        System.out.printf("|  HP %-2d / %-2d   MP %-2d / %-2d       : %-6s : [4] 스킬3                 |\n",
            dragonHP, dragonMaxHP, dragonMP, dragonMaxMP, "");
        System.out.println("+----------------------------------------------------------------------+");
    }
}