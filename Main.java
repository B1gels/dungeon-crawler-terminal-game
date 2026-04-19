import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void showHelper(){
        System.out.println("\n==== perintah yang tersedia ====");
        System.out.println("w: atas");
        System.out.println("s: bawah");
        System.out.println("a: kiri");
        System.out.println("d: kanan");
        System.out.println("stats : melihat statistik player");
        System.out.println("inventory : mengecek inventory");
        System.out.println("use : menggunakan item");
        System.out.println("exit : keluar dari permainan ");

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Player player = new Player();
        Random rand = new Random();
        Map map = new Map(10, 10);
        boolean running = true;
        int result = 0;
        String eventMessage = "";

        
        while (running) {
            try {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                pb.inheritIO();
                Process process = pb.start();
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            System.out.println("--- ketik help untuk melihat perintah yang ada ---");

            // render dulu
            map.render(player);

            // tampilkan pesan kalau misalnya ada kegagalan
            if (result == 1) {
                System.out.println("\n-- input tidak valid --\n");
            } else if (result == 2) {
                System.out.println("\n-- nabrak tembok --\n");
            }

            // tampilkan pesan dari kejadian yang terjadi
            if (!eventMessage.equals("")) {
                System.out.println(eventMessage);
            }

            System.out.print("masukkan perintah : ");
            String direction = input.nextLine();
            
              // cek kalau user mau melihat stats nya
            if (direction.equalsIgnoreCase("help")) {
                showHelper();
                System.out.print("\n(tekan ENTER untuk kembali)");
                input.nextLine();
                continue;
            }

            // cek kalau user mau melihat stats nya
            if (direction.equalsIgnoreCase("stats")) {
                player.showStats();
                System.out.print("\n(tekan ENTER untuk kembali)");
                input.nextLine();
                continue;
            }

            // cek kalau user mau melihat inventory
            if (direction.equalsIgnoreCase("inventory")) {
                String cekInventory = player.showInventory();
                System.out.println(cekInventory);
                System.out.print("\n(tekan ENTER untuk kembali)");
                input.nextLine();
                continue;
            }

            // cek kalau user mau keluar dari permainan
            if (direction.equalsIgnoreCase("exit")) {
                System.out.println("=== anda keluar dari permainan ===");
                break;
            }


            eventMessage = ""; // kosongkan pesan setelah di tampilkan sebelumnya

            // cek kalau user mau menggunakan item didalam inventory
            if (direction.equalsIgnoreCase("use")) {
                String cekInventory = player.showInventory();
                if (cekInventory.equals("\nInventori kosong")) {
                    System.out.println(cekInventory);
                    System.out.print("\n(tekan ENTER untuk kembali)");
                    input.nextLine();
                    continue;
                }

                System.out.print("\n(silahkan pilih item yang ingin digunakan : ");
                String pickItem = input.nextLine();
                player.useItem(Integer.parseInt(pickItem));
                continue;
            }

            result = player.move(direction, map); // gerakkan player

            // jalankan event tertentu jika berhasil jalan
            if (result == 0) {
                int rollEvent = rand.nextInt(3);

                // acak event tertentu secara random kalau player jalan
                if (rollEvent == 0) {
                    eventMessage = "aman";
                } else if (rollEvent == 1) {
                    int rollItem = rand.nextInt(3);

                    Item item;

                    // dapat item random
                    if (rollItem == 0) {
                        item = new Item(Item.ItemType.POTION, 20);
                    } else if (rollItem == 1) {
                        item = new Item(Item.ItemType.ELIXIR, 50);
                    } else {
                        item = new Item(Item.ItemType.SWORD, 30);
                    }

                    player.addItem(item);
                    eventMessage = "kamu mendapatkan " + item.type;
                } else if (rollEvent == 2) {
                    Monster monster = Monster.generateMonster(player);
                    boolean battleResult = Battle.battleEvent(player, monster);

                    System.out.print("\n(tekan ENTER untuk lanjut)");
                    input.nextLine();

                    if (!battleResult) {
                        running = false;
                        System.out.println("xx GAME OVER xx");
                    }
                }
            }
        }

        input.close();
    }
}