import java.util.Random;

public class Map {
    Random rand = new Random();

    int width;
    int height;
    char[][] grid;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;

        grid = new char[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = '.';
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = '.';
            }
        }

        int jumlahObstacle = (width * height) / 5;
        for (int i = 0; i < jumlahObstacle; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if(grid[x][y] == '.' && x != 0 && y !=0){
                grid[x][y] = 'X';
            }
        }

    }

    void printBorder(int width) {
    System.out.print("+");
    
    for (int i = 0; i < width * 2 + 1; i++) {
        System.out.print("-");
    }
    
    System.out.println("+");
}

    public void render(Player player) {
        printBorder(width);

        for (int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < grid[i].length; j++) {
                if (i == player.posX && j == player.posY) {
                    System.out.print("p ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }

            }
            System.out.print("|");
            System.out.println("");
        }

        printBorder(width);

    }

    public boolean isValidPosition(int posX, int posY) {
        if (posX < 0 || posY < 0)
            return false;

        if (posX >= height || posY >= width)
            return false;

        // cek ada tembok apa tidak
        if (grid[posX][posY] == 'X')
            return false;

        return true;

    }

    
}
