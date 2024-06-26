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
    
    private boolean ifMoveNorthPossible() {
        for (int x = 0; x < wi; x++) {
            for (int y = 1; y < he; y++) {
                if (bord[y][x] != 0 && (bord[y - 1][x] == 0 || bord[y - 1][x] == bord[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean ifMoveSouthPossible() {
        for (int x = 0; x < wi; x++) {
            for (int y = he - 2; y >= 0; y--) {
                if (bord[y][x] != 0 && (bord[y + 1][x] == 0 || bord[y + 1][x] == bord[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean ifMoveWestPossible() {
        for (int y = 0; y < he; y++) {
            for (int x = 1; x < wi; x++) {
                if (bord[y][x] != 0 && (bord[y][x - 1] == 0 || bord[y][x - 1] == bord[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean ifMoveEastPossible() {
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
                return ifMoveNorthPossible();
            case SOUTH:
                return ifMoveSouthPossible();
            case WEST:
                return ifMoveWestPossible();
            case EAST:
                return ifMoveEastPossible();
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


    private boolean moveUp() {
    boolean moved = false;

    for (int col = 0; col < wi; col++) {
        int count = 0;
        for (int row = 0; row < he; row++) {
            if (bord[row][col] != 0) {
                bord[count][col] = bord[row][col];
                if (count != row) {
                    bord[row][col] = 0;
                    moved = true;
                }
                count++;
            }
        }
    }
    for (int col = 0; col < wi; col++) {
        for (int row = 0; row < he - 1; row++) {
            if (bord[row][col] != 0 && bord[row][col] == bord[row + 1][col]) {
                bord[row][col] *= 2;
                bord[row + 1][col] = 0;
                points += bord[row][col];
                moved = true;
                break;
            }
        }
    }

    for (int col = 0; col < wi; col++) {
        int count = 0;
        for (int row = 0; row < he; row++) {
            if (bord[row][col] != 0) {
                bord[count][col] = bord[row][col];
                if (count != row) {
                    bord[row][col] = 0;
                    moved = true;
                }
                count++;
            }
        }
    }

    return moved;
}


private boolean moveLeft() {
    boolean moved = false;

    for (int row = 0; row < he; row++) {
        int count = 0;
        for (int col = 0; col < wi; col++) {
            if (bord[row][col] != 0) {
                bord[row][count] = bord[row][col];
                if (count != col) {
                    bord[row][col] = 0;
                    moved = true;
                }
                count++;
            }
        }
    }
    for (int row = 0; row < he; row++) {
        for (int col = 0; col < wi - 1; col++) {
            if (bord[row][col] != 0 && bord[row][col] == bord[row][col + 1]) {
                bord[row][col] *= 2;
                bord[row][col + 1] = 0;
                points += bord[row][col];
                moved = true;
                break; 
            }
        }
    }


    for (int row = 0; row < he; row++) {
        int count = 0;
        for (int col = 0; col < wi; col++) {
            if (bord[row][col] != 0) {
                bord[row][count] = bord[row][col];
                if (count != col) {
                    bord[row][col] = 0;
                    moved = true;
                }
                count++;
            }
        }
    }

    return moved;
}


private boolean moveRight() {
    boolean moved = false;


    for (int row = 0; row < he; row++) {
        int count = 0;
        for (int col = wi - 1; col >= 0; col--) {
            if (bord[row][col] != 0) {
                bord[row][wi - 1 - count] = bord[row][col];
                if (wi - 1 - count != col) {
                    bord[row][col] = 0;
                    moved = true;
                }
                count++;
            }
        }
    }

    for (int row = 0; row < he; row++) {
        for (int col = wi - 1; col > 0; col--) {
            if (bord[row][col] != 0 && bord[row][col] == bord[row][col - 1]) {
                bord[row][col] *= 2;
                bord[row][col - 1] = 0;
                points += bord[row][col];
                moved = true;
                break; 
            }
        }
    }

    for (int row = 0; row < he; row++) {
        int count = 0;
        for (int col = wi - 1; col >= 0; col--) {
            if (bord[row][col] != 0) {
                bord[row][wi - 1 - count] = bord[row][col];
                if (wi - 1 - count != col) {
                    bord[row][col] = 0;
                    moved = true;
                }
                count++;
            }
        }
    }

    return moved;
}

private boolean moveDown() {
    boolean moved = false;

 
    for (int col = 0; col < wi; col++) {
        int count = 0;
        for (int row = he - 1; row >= 0; row--) {
            if (bord[row][col] != 0) {
                bord[he - 1 - count][col] = bord[row][col];
                if (he - 1 - count != row) {
                    bord[row][col] = 0;
                    moved = true;
                }
                count++;
            }
        }
    }
    for (int col = 0; col < wi; col++) {
        for (int row = he - 1; row > 0; row--) {
            if (bord[row][col] != 0 && bord[row][col] == bord[row - 1][col]) {
                bord[row][col] *= 2;
                bord[row - 1][col] = 0;
                points += bord[row][col];
                moved = true;
                break; 
            }
        }
    }
    for (int col = 0; col < wi; col++) {
        int count = 0;
        for (int row = he - 1; row >= 0; row--) {
            if (bord[row][col] != 0) {
                bord[he - 1 - count][col] = bord[row][col];
                if (he - 1 - count != row) {
                    bord[row][col] = 0;
                    moved = true;
                }
                count++;
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
                return false;
        }
        if (moved) {
            moveCount++;
        }
        return moved;
    }
    
    @Override
    public void run(PlayerInterface player, UserInterface ui) {
        if (player == null || ui == null) {
            throw new IllegalArgumentException("Player and UI cannot be null.");
        }
        addPiece();
        addPiece();
        while (!isGameOver()){
            ui.updateScreen(this);
            MoveDirection move = player.getPlayerMove(  this, ui);
            boolean moved = performMove(move);
            if (!moved) {
                continue;
            }

            
            addPiece();

          
            moveCount++;
        }

       
        ui.showGameOverScreen(this);
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

    private boolean isGameOver() {
        
        if(!isMovePossible())
        {return true;}
        return false;
     }
    
  
     
}
