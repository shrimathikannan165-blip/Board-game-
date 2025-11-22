import java.util.*;

class Board {

    private final int BOARD_SIZE = 100; // Using assignment operator
    private Map<Integer, Integer> moves = new HashMap<>();

    public Board() {
        // Snakes
        moves.put(17, 7);
        moves.put(54, 34);
        moves.put(62, 19);
        moves.put(98, 79);

        // Ladders
        moves.put(3, 38);
        moves.put(28, 84);
        moves.put(58, 77);
        moves.put(75, 86);
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public boolean hasMove(int position) {
        return moves.containsKey(position);
    }

    public int getNewPosition(int position) {
        return moves.get(position);
    }
}

// --------------------------------------------

class Player {
    private String name;
    private int position = 0; // assignment operator (=)

    public Player(String name) {
        this.name = name;
    }

    public void updatePosition(int move) {
        position += move; // += operator
    }

    public void setPosition(int newPos) {
        position = newPos;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}

// --------------------------------------------

class Game {

    private Board board;
    private Player player;
    private Random random = new Random();

    public Game(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public int rollDice() {
        return random.nextInt(6) + 1; // arithmetic operator (+)
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Snake and Ladder Game Started (OOP Version) ---");

        while (player.getPosition() < board.getBoardSize()) {

            System.out.print("\nPress Enter to roll dice...");
            sc.nextLine();

            int dice = rollDice();
            System.out.println(player.getName() + " rolled: " + dice);

            int oldPosition = player.getPosition();
            player.updatePosition(dice);

            // overshoot check
            if (player.getPosition() > board.getBoardSize()) {
                System.out.println("Overshoot! Move cancelled.");
                player.setPosition(oldPosition); // resetting position
                continue;
            }

            System.out.println("Moved to: " + player.getPosition());

            // snake or ladder check
            if (board.hasMove(player.getPosition())) {
                int newPos = board.getNewPosition(player.getPosition());

                // ternary operator usage
                String type = (newPos > player.getPosition()) ? "Ladder!" : "Snake!";

                System.out.println(type + " Move to: " + newPos);
                player.setPosition(newPos);
            }

            // win check
            if (player.getPosition() == board.getBoardSize()) {
                System.out.println("\n🎉 " + player.getName() + " reached 100 and won the game!");
                break;
            }
        }

        sc.close();
    }
}

// --------------------------------------------

public class SnakeAndLadder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Player Name: ");
        String name = sc.nextLine();

        Board board = new Board();
        Player player = new Player(name);
        Game game = new Game(board, player);

        game.start();

        sc.close();
    }
}

