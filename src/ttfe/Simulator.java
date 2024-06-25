package ttfe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator implements SimulatorInterface {
    private int wi;
	private int he;
	private Random ran;
	private int moveCount;
	private int points;
    private int bord[][];
  
    public Simulator(int width, int height, Random random) {
		if (width < 2 || height < 2) {
            throw new IllegalArgumentException("Width and height must be at least 2.");
        }
        if (random == null) {
            throw new IllegalArgumentException("Random object cannot be null.");
        }
		this.wi = width;
		this.he = height;
		this.ran = random;
        this.moveCount = 0;
		this.points = 0;
        this.bord=new int[height][width];
        addPiece();
        addPiece();
	}
    @Override
    public void addPiece() {
   
    if (!isSpaceLeft()) {
        throw new IllegalStateException("The board is already full.");
    }

   
    int value = ran.nextDouble() < 0.9 ? 2 : 4;

   
    List<int[]> emptyPositions = new ArrayList<>();
    for (int y = 0; y < he; y++) {
        for (int x = 0; x < wi; x++) {
            if (bord[y][x] == 0) {
                emptyPositions.add(new int[]{x, y});
            }
        }
    }

   
    int[] position = emptyPositions.get(ran.nextInt(emptyPositions.size()));

 
    bord[position[1]][position[0]] = value;
}

    @Override
    public int getBoardHeight() {
        return he;
    }
    @Override
    public int getBoardWidth() {
        return wi;
    }
    @Override
    public int getNumMoves() {
       return moveCount;
    }
    @Override
    public int getNumPieces() {
        int z=0;
        for (int y = 0; y < he; y++) {
			for (int x = 0; x < wi; x++) {
				if (bord[y][x] != 0) {
					z+=1;
				}
			}
		}
        return z;
    }
    @Override
    public int getPieceAt(int x, int y) {
        if (x < 0 || x >= wi || y < 0 || y >= he) {
			throw new IllegalArgumentException("Invalid coordinates.");
		}
		
		return bord[y][x];
	

    }
    @Override
    public int getPoints() {
      return points;
    }









    @Override
    public boolean isMovePossible() {
        for (MoveDirection direction : MoveDirection.values()) {
            if (isMovePossible(direction)) {
                return true;
            }
        }
        return false;
    }
    private boolean ifmovenorthpossible(){
        for (int x = 0; x < wi; x++) {
            for (int y = 1; y < he; y++) {
                if (bord[y][x] != 0 && (bord[y - 1][x] == 0 || bord[y - 1][x] == bord[y][x])) {
                    return true;
                }
            }
        }
        return false;
    

    }
    private boolean ifmovesouthpossible(){
        for (int x = 0; x < wi; x++) {
            for (int y = he - 2; y >= 0; y--) {
                if (bord[y][x] != 0 && (bord[y + 1][x] == 0 || bord[y + 1][x] == bord[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean ifmovewestpossible(){
        for (int y = 0; y < he; y++) {
            for (int x = 1; x < wi; x++) {
                if (bord[y][x] != 0 && (bord[y][x - 1] == 0 || bord[y][x - 1] == bord[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean ismoveeastpossible(){
        for (int y = 0; y < he; y++) {
            for (int x = wi - 2; x >= 0; x--) {
                if (bord[y][x] != 0 && (bord[y][x + 1] == 0 || bord[y][x + 1] == bord[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }




    


    
    @Override
    public boolean isMovePossible(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null.");
        }
        switch (direction) {
            case NORTH:
                return ifmovenorthpossible();
            case SOUTH:
                return ifmovesouthpossible();
            case WEST:
                return ifmovewestpossible();
            case EAST:
                return ismoveeastpossible();
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }
    
    @Override
    public boolean isSpaceLeft() {
        for (int y = 0; y < he; y++) {
			for (int x = 0; x < wi; x++) {
				if (bord[y][x] == 0) {
					return true;
				}
			}
		}
		return false;
    }
/* */
private boolean moveUp() {
    boolean moved = false;
    // Loop through each column
    for (int x = 0; x < wi; x++) {
        // Use an array to track merged tiles for this column
        int[] merged = new int[he];
        
        // Start from the second row and move upwards
        for (int y = 1; y < he; y++) {
            if (bord[y][x] != 0) {
                int newY = y;
                
                // Move the tile upwards as far as there are empty spaces
                while (newY > 0 && (bord[newY - 1][x] == 0 || bord[newY - 1][x] == bord[y][x])) {
                    if (bord[newY - 1][x] == 0) {
                        // Move the tile up
                        bord[newY - 1][x] = bord[newY][x];
                        bord[newY][x] = 0;
                        newY--;
                        moved = true;
                    } else if (bord[newY - 1][x] == bord[y][x] && merged[newY - 1] == 0) {
                        // Merge with the tile above
                        bord[newY - 1][x] *= 2;
                        bord[newY][x] = 0;
                        points += bord[newY - 1][x];
                        merged[newY - 1] = 1;
                        moved = true;
                        break; // Exit the loop after merging once
                    }
                }
            }
        }
    }
    return moved;
}

   /* private boolean moveDown() {
        boolean moved = false;
        for (int x = 0; x < wi; x++) {
            int[] merged = new int[he];
            for (int y = he - 2; y >= 0; y--) {
                if (bord[y][x] != 0) {
                    int newY = y;
                    while (newY < he - 1 && bord[newY + 1][x] == 0) {
                        bord[newY + 1][x] = bord[newY][x];
                        bord[newY][x] = 0;
                        newY++;
                        moved = true;
                    }
                    if (newY < he - 1 && bord[newY + 1][x] == bord[newY][x] && merged[newY + 1] == 0) {
                        bord[newY + 1][x] *= 2;
                        bord[newY][x] = 0;
                        points += bord[newY + 1][x];
                        merged[newY + 1] = 1;
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }
    private boolean moveLeft() {
        boolean moved = false;
        for (int y = 0; y < he; y++) {
            int[] merged = new int[wi];
            for (int x = 1; x < wi; x++) {
                if (bord[y][x] != 0) {
                    int newX = x;
                    while (newX > 0 && bord[y][newX - 1] == 0) {
                        bord[y][newX - 1] = bord[y][newX];
                        bord[y][newX] = 0;
                        newX--;
                        moved = true;
                    }
                    if (newX > 0 && bord[y][newX - 1] == bord[y][newX] && merged[newX - 1] == 0) {
                        bord[y][newX - 1] *= 2;
                        bord[y][newX] = 0;
                        points += bord[y][newX - 1];
                        merged[newX - 1] = 1;
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }
    private boolean moveRight() {
        boolean moved = false;
        for (int y = 0; y < he; y++) {
            int[] merged = new int[wi];
            for (int x = wi - 2; x >= 0; x--) {
                if (bord[y][x] != 0) {
                    int newX = x;
                    while (newX < wi - 1 && bord[y][newX + 1] == 0) {
                        bord[y][newX + 1] = bord[y][newX];
                        bord[y][newX] = 0;
                        newX++;
                        moved = true;
                    }
                    if (newX < wi - 1 && bord[y][newX + 1] == bord[y][newX] && merged[newX + 1] == 0) {
                        bord[y][newX + 1] *= 2;
                        bord[y][newX] = 0;
                        points += bord[y][newX + 1];
                        merged[newX + 1] = 1;
                        moved = true;
                    }
                }
            }
        }
       
        return moved;
    }*/ 
    private boolean moveDown() {
        boolean moved = false;
        // Loop through each column
        for (int x = 0; x < wi; x++) {
            // Use an array to track merged tiles for this column
            int[] merged = new int[he];
            
            // Start from the second last row and move downwards
            for (int y = he - 2; y >= 0; y--) {
                if (bord[y][x] != 0) {
                    int newY = y;
                    
                    // Move the tile downwards as far as there are empty spaces
                    while (newY < he - 1 && (bord[newY + 1][x] == 0 || bord[newY + 1][x] == bord[y][x])) {
                        if (bord[newY + 1][x] == 0) {
                            // Move the tile down
                            bord[newY + 1][x] = bord[newY][x];
                            bord[newY][x] = 0;
                            newY++;
                            moved = true;
                        } else if (bord[newY + 1][x] == bord[y][x] && merged[newY + 1] == 0) {
                            // Merge with the tile below
                            bord[newY + 1][x] *= 2;
                            bord[newY][x] = 0;
                            points += bord[newY + 1][x];
                            merged[newY + 1] = 1;
                            moved = true;
                            break; // Exit the loop after merging once
                        }
                    }
                }
            }
        }
        return moved;
    }
    private boolean moveLeft() {
        boolean moved = false;
        // Loop through each row
        for (int y = 0; y < he; y++) {
            // Use an array to track merged tiles for this row
            int[] merged = new int[wi];
            
            // Start from the second column and move leftwards
            for (int x = 1; x < wi; x++) {
                if (bord[y][x] != 0) {
                    int newX = x;
                    
                    // Move the tile leftwards as far as there are empty spaces
                    while (newX > 0 && (bord[y][newX - 1] == 0 || bord[y][newX - 1] == bord[y][x])) {
                        if (bord[y][newX - 1] == 0) {
                            // Move the tile left
                            bord[y][newX - 1] = bord[y][newX];
                            bord[y][newX] = 0;
                            newX--;
                            moved = true;
                        } else if (bord[y][newX - 1] == bord[y][x] && merged[newX - 1] == 0) {
                            // Merge with the tile to the left
                            bord[y][newX - 1] *= 2;
                            bord[y][newX] = 0;
                            points += bord[y][newX - 1];
                            merged[newX - 1] = 1;
                            moved = true;
                            break; // Exit the loop after merging once
                        }
                    }
                }
            }
        }
        return moved;
    }
    private boolean moveRight() {
        boolean moved = false;
        // Loop through each row
        for (int y = 0; y < he; y++) {
            // Use an array to track merged tiles for this row
            int[] merged = new int[wi];
            
            // Start from the second last column and move rightwards
            for (int x = wi - 2; x >= 0; x--) {
                if (bord[y][x] != 0) {
                    int newX = x;
                    
                    // Move the tile rightwards as far as there are empty spaces
                    while (newX < wi - 1 && (bord[y][newX + 1] == 0 || bord[y][newX + 1] == bord[y][x])) {
                        if (bord[y][newX + 1] == 0) {
                            // Move the tile right
                            bord[y][newX + 1] = bord[y][newX];
                            bord[y][newX] = 0;
                            newX++;
                            moved = true;
                        } else if (bord[y][newX + 1] == bord[y][x] && merged[newX + 1] == 0) {
                            // Merge with the tile to the right
                            bord[y][newX + 1] *= 2;
                            bord[y][newX] = 0;
                            points += bord[y][newX + 1];
                            merged[newX + 1] = 1;
                            moved = true;
                            break; // Exit the loop after merging once
                        }
                    }
                }
            }
        }
        return moved;
    }
            
    





    @Override
    public boolean performMove(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null.");
        }
        boolean moved = false;
        switch (direction) {
            case NORTH:
                moved = moveUp();
                break;
            case SOUTH:
                moved = moveDown();
                break;
            case WEST:
                moved = moveLeft();
                break;
            case EAST:
                moved = moveRight();
                break;
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
        if (moved) {
            addPiece();
            moveCount++;
        }
        return moved;
    }
    
    @Override
    public void run(PlayerInterface player, UserInterface ui) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    @Override
    public void setPieceAt(int x, int y, int piece) {
        if (x < 0 || x >= wi || y < 0 || y >= he) {
			throw new IllegalArgumentException("Invalid coordinates.");
		}
		if (piece < 0) {
			throw new IllegalArgumentException("Piece value cannot be negative.");
		}
		bord[y][x] = piece;
	}

    
}
