package ttfe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computerp implements PlayerInterface {

    private Random random;

    public Computerp() {
        this.random = new Random();
    }

    @Override
    public MoveDirection getPlayerMove(SimulatorInterface game, UserInterface ui) {
        MoveDirection[] directions = MoveDirection.values();
        shuffleArray(directions); // Randomize move order for variety
        
        int bestScore = Integer.MIN_VALUE;
        MoveDirection bestMove = null;

        for (MoveDirection direction : directions) {
            // Evaluate the move by performing it directly
            if (game.performMove(direction)) {
                int score = evaluateScore(game);
                // Undo the move immediately to keep the game state unchanged
                game.performMove(reverseDirection(direction));

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = direction;
                }
            }
        }

        return bestMove != null ? bestMove : directions[0]; // Fallback move (shouldn't normally happen)
    }

    private MoveDirection reverseDirection(MoveDirection direction) {
        switch (direction) {
            case NORTH:
                return MoveDirection.NORTH;
            case SOUTH:
                return MoveDirection.SOUTH;
            case WEST:
                return MoveDirection.WEST;
            case EAST:
                return MoveDirection.EAST;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    private int evaluateScore(SimulatorInterface game) {
        int actualScore = game.getPoints();
        int numberOfEmptyCells = countEmptyCells(game);
        int clusteringScore = calculateClusteringScore(game);

        // Heuristic function similar to Vasilis Vryniotis' approach
        int score = (int) (actualScore + Math.log(actualScore) * numberOfEmptyCells - clusteringScore);
        return Math.max(score, Math.min(actualScore, 1)); // Ensure non-negative score
    }

    private int countEmptyCells(SimulatorInterface game) {
        int height = game.getBoardHeight();
        int width = game.getBoardWidth();
        int count = 0;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (game.getPieceAt(row, col) == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    private int calculateClusteringScore(SimulatorInterface game) {
        int clusteringScore = 0;
        int height = game.getBoardHeight();
        int width = game.getBoardWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = game.getPieceAt(i, j);
                if (value != 0) {
                    int neighbors = 0;
                    int totalDifference = 0;

                    // Up
                    if (i > 0) {
                        neighbors++;
                        totalDifference += Math.abs(game.getPieceAt(i - 1, j) - value);
                    }
                    // Down
                    if (i < height - 1) {
                        neighbors++;
                        totalDifference += Math.abs(game.getPieceAt(i + 1, j) - value);
                    }
                    // Left
                    if (j > 0) {
                        neighbors++;
                        totalDifference += Math.abs(game.getPieceAt(i, j - 1) - value);
                    }
                    // Right
                    if (j < width - 1) {
                        neighbors++;
                        totalDifference += Math.abs(game.getPieceAt(i, j + 1) - value);
                    }

                    if (neighbors > 0) {
                        clusteringScore += totalDifference / neighbors;
                    }
                }
            }
        }

        return clusteringScore;
    }

    private void shuffleArray(MoveDirection[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Swap
            MoveDirection temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
