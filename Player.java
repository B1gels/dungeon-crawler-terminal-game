import java.util.ArrayList;
import java.util.List;

public class Player {

    // attribut Player
    int HP;
    int attack;
    int defense;
    int posX;
    int posY;
    List<Item> Inventory = new ArrayList<>();

    // constructor class Player
    public Player() {
        HP = 40;
        defense = 10;
        attack = 10;
        posX = 0;
        posY = 0;
    }

    // methods untuk menampilakn statisitk
    public void showStats() {
        System.out.println("\n==== statistik kamu ====");
        System.out.println("HP: " + this.HP);
        System.out.println("defense: " + this.defense);
        System.out.println("attack: " + this.attack);
    }

    // methods untuk menambahkan item ke dalam inventory
    public void addItem(Item item) {
        Inventory.add(item);
    }

    // methods untuk menampilkan isi inventory
    public String showInventory() {
        if (Inventory.isEmpty()) {
            return "\nInventori kosong";
            
        }

        System.out.println("\n=== Inventory ===");
        for (int i = 0; i < Inventory.size(); i++) {
            Item item = Inventory.get(i);
            System.out.println((i) + ". " + item.type + " (+" + item.value + " " + item.name + ")");
        }

        return "";
    }

    // methods untuk menggunakan item dari inventory
    public String useItem(int index) {
        if (Inventory.isEmpty()) {
            return "\nInventori kosong";
        }

        if(index < 0 || index >= Inventory.size()){
            return "\n pilihan tidak valid";
        }

        // mengambil item berdasarkan yang dipilih player
        Item item = Inventory.get(index);

        switch (item.type) {
            case POTION:
                this.HP += item.value;
                break;
            case ELIXIR:
                this.defense += item.value;
                break;
            case SWORD:
                this.attack += item.value;
                break;
        }

        Inventory.remove(index);

        return "berhasil menggunakan item : " + item.type;
    }

    // methods move untuk player + pesan
    public int move(String Direction, Map Board) {
        int newX = posX;
        int newY = posY;

        // kondisi ketika player memasukkan input [w,a,s,d]
        if (Direction.equalsIgnoreCase("w")) {
            newX -= 1;
        } else if (Direction.equalsIgnoreCase("s")) {
            newX += 1;
        } else if (Direction.equalsIgnoreCase("a")) {
            newY -= 1;
        } else if (Direction.equalsIgnoreCase("d")) {
            newY += 1;
        } else {
            return 1;
        }

        // mengecek apakah posisi selanjutnya dari player itu valid atau tidak (masih di
        // dalam map apa sudah keluar)
        if (Board.isValidPosition(newX, newY)) {
            this.posX = newX;
            this.posY = newY;
        } else {
            return 2;
        }

        return 0;

    }
}
