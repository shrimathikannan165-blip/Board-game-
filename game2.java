import java.util.*;

// ------------------- Custom Exception -----------------------

class InvalidDiceException extends Exception {
    public InvalidDiceException(String msg) {
        super(msg);
    }
}

// ------------------------- BOARD ----------------------------

class Board {

    private final int BOARD_SIZE = 100;
    private Map<Integer, Integer> moves = new HashMap<>();

    public Board() {
        try {
            moves.put(17, 7);
            moves.put(54, 34);
            moves.put(62, 19);
            moves.put(98, 79);

            moves.put(3, 38);
            moves.put(28, 84);
            moves.put(58, 77);
            moves.put(75, 86);
        } 
        catch (Exception e) {
            System.out.println("Error loading board: " + e.getMessage());
        }
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

// ------------------------- PLAYER -----------------------------

class Player {
    private String name;
    private int position = 0;

    public Player(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Player name cannot be empty!");
            }
            this.name = name;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid Name! Setting default name: Player");
            this.name = "Player";
        }
    }

    public void updatePosition(int move) {
        position += move;
    }

    public void setPosition(int newPos) {
        this.position = newPos;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}

// --------------------------- GAME -----------------------------

class Game {

    private Board board;
    private Player player;
    private Random random = new Random();

    public Game(Board board, Player player) {
        try {
            if (board == null || player == null) {
                throw new NullPointerException("Board or Player cannot be null!");
            }
            this.board = board;
            this.player = player;
        }
        catch (NullPointerException e) {
            System.out.println("Game initialization error: " + e.getMessage());
        }
    }

    public int rollDice() throws InvalidDiceException {
        int value = random.nextInt(6) + 1;

        if (value < 1 || value > 6) {
            throw new InvalidDiceException("Dice rolled an invalid value!");
        }
        return value;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Snake and Ladder Game Started (OOP + Exception Handling) ---");

        while (player.getPosition() < board.getBoardSize()) {

            System.out.print("\nPress Enter to roll dice...");

            try {
                sc.nextLine(); // any input is handled
            }
            catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
            }

            int dice = 0;

            try {
                dice = rollDice();
                System.out.println(player.getName() + " rolled: " + dice);
            }
            catch (InvalidDiceException e) {
                System.out.println("Dice error: " + e.getMessage());
                continue;
            }

            int oldPosition = player.getPosition();
            player.updatePosition(dice);

            if (player.getPosition() > board.getBoardSize()) {
                System.out.println("Overshoot! Move cancelled.");
                player.setPosition(oldPosition);
                continue;
            }

            System.out.println("Moved to: " + player.getPosition());

            if (board.hasMove(player.getPosition())) {
                int newPos = board.getNewPosition(player.getPosition());
                String type = (newPos > player.getPosition()) ? "Ladder!" : "Snake!";

                System.out.println(type + " Move to: " + newPos);
                player.setPosition(newPos);
            }

            if (player.getPosition() == board.getBoardSize()) {
                System.out.println("\n🎉 " + player.getName() + " reached 100 and won the game!");
                break;
            }
        }

        sc.close();
    }
}

// -------------------------- MAIN ------------------------------

public class SnakeAndLadder {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String name = "";
        try {
            System.out.print("Enter Player Name: ");
            name = sc.nextLine();
        }
        catch (Exception e) {
            System.out.println("Error reading name. Setting default name.");
            name = "Player";
        }

        Board board = new Board();
        Player player = new Player(name);
        Game game = new Game(board, player);

        game.start();

        sc.close();
    }
}
