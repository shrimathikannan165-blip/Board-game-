import java.util.*;

public class SnakeAndLadder {

    // Board size using assignment operator
    private static final int BOARD_SIZE = 100;

    // Snake and Ladder positions (start → end)
    static Map<Integer, Integer> moves = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        // Adding snakes (uses - operator) and ladders (+ operator)
        moves.put(17, 7); // Snake
        moves.put(54, 34); // Snake
        moves.put(62, 19); // Snake
        moves.put(98, 79); // Snake

        moves.put(3, 38); // Ladder
        moves.put(28, 84); // Ladder
        moves.put(58, 77); // Ladder
        moves.put(75, 86); // Ladder

        System.out.print("Enter Player Name: ");
        String player = sc.nextLine();

        int position = 0; // assignment operator (=)

        System.out.println("\n--- Snake and Ladder Game Started ---");

        while (position < BOARD_SIZE) { // comparison operator (<)

            System.out.print("\nPress Enter to roll dice...");
            sc.nextLine();

            int dice = r.nextInt(6) + 1; // arithmetic operator (+)
            System.out.println(player + " rolled: " + dice);

            position += dice; // assignment operator (+=)

            // If overshoot, stay at last position (logical operator &&)
            if (position > BOARD_SIZE) {
                System.out.println("Overshoot! Move cancelled.");
                position -= dice; // -= operator
                continue;
            }

            System.out.println("Moved to: " + position);

            // Check snake or ladder (comparison operator ==)
            if (moves.containsKey(position)) {
                int newPos = moves.get(position);

                // Ternary operator to check snake or ladder
                String type = (newPos > position) ? "Ladder!" : "Snake!";

                System.out.println(type + " Move to: " + newPos);

                position = newPos; // assignment operator
            }

            // Check win condition using comparison operator (==)
            if (position == BOARD_SIZE) {
                System.out.println("\n🎉 " + player + " reached 100 and won the game!");
                break;
            }
        }

        sc.close();
    }
}

