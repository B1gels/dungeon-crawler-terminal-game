import java.util.Scanner;
import java.util.Random;

public class Battle {

    public static boolean battleEvent(Player player, Monster monster) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("-- Monster Terlihat --");
        while (player.HP > 0 && monster.HP > 0) {
            System.out.println("\n==== pilih aksi ====");
            System.out.println("1.Serang");
            System.out.println("2.Lari");
            System.out.print("Masukkan pilhan mu : ");
            String choice = input.nextLine();

            if (!choice.equalsIgnoreCase("1") && !choice.equalsIgnoreCase("2")) {
                System.out.println("\n input tidak valid \n");
                continue;
            }

            if (choice.equalsIgnoreCase("1")) {
                int damageToMonster = player.attack - (monster.defense / 2);
                if (damageToMonster < 1)
                    damageToMonster = 1;

                monster.HP -= damageToMonster;
                if (monster.HP < 0)
                    monster.HP = 0;

                System.out.println("||kamu menyerang monster, Damage: " + damageToMonster + "\n");
                monster.showStats();

                if (monster.HP <= 0){
                    System.err.println("\n-xx Monster Kalah -xx\n");
                    break;
                }

                int damageToPlayer = monster.attack - (player.defense / 2);
                if (damageToPlayer < 1)
                    damageToPlayer = 1;

                player.HP -= damageToPlayer;
                if (player.HP < 0)
                    player.HP = 0;

                System.out.println("||Monster menyerang kamu, damage : " + damageToPlayer + "\n");
                player.showStats();

            }

            if (choice.equalsIgnoreCase("2")) {
                boolean success = rand.nextBoolean();

                if (success) {
                    break;
                } else {
                    int damageToPlayer = monster.attack - (player.defense / 2);
                    if (damageToPlayer < 1)
                        damageToPlayer = 1;

                    player.HP -= damageToPlayer;
                    if (player.HP < 0)
                        player.HP = 0;


                    System.out.println("kamu gagl melarikan dir Monster menyerang kamu, damage : " + damageToPlayer);
                    player.showStats();
                }
            }

        }


        if (player.HP <= 0) {
            System.out.println("-xx kamu tewas saat melawan monster -xx");
            return false;
        }

        return true;
    }

}
