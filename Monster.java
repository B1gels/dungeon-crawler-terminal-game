import java.util.Random;

public class Monster {
    int HP;
    int attack;
    int defense;

    public Monster(int HP, int attack, int defense) {
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
    }

    // methods untuk menampilakn statisitk
    public void showStats() {
        System.out.println("\n==== statistik monster ====");
        System.out.println("HP: " + this.HP);
        System.out.println("defense: " + this.defense);
        System.out.println("attack: " + this.attack);
    }

    public static Monster generateMonster(Player player) {

        Random rand = new Random();

        int hp = (int) (player.HP * 0.6);
        int attack = player.attack + rand.nextInt(-2, 2);
        if (attack < 1)
            attack = 1;

        int defense = player.defense + rand.nextInt(-2, 2);
        if (defense < 0)
            defense = 0;
        if (defense > 100)
            defense = 100;

        return new Monster(hp, attack, defense);
    }
}
