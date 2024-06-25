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
    private boolean moveAndMergeLeft(int[] line) {
        boolean moved = false;
        int[] newLine = new int[line.length];
        int[] merged = new int[line.length];
        int target = 0;
        
        for (int i = 0; i < line.length; i++) {
            if (line[i] != 0) {
                if (target > 0 && newLine[target - 1] == line[i] && merged[target - 1] == 0) {
                    newLine[target - 1] *= 2;
                    points += newLine[target - 1];
                    merged[target - 1] = 1;
                    moved = true;
                } else {
                    newLine[target] = line[i];
                    if (target != i) {
                        moved = true;
                    }
                    target++;
                }
            }
        }
        
        System.arraycopy(newLine, 0, line, 0, line.length);
        return moved;
    }
    
    private boolean moveUp() {
        boolean moved = false;
        for (int x = 0; x < wi; x++) {
            int[] column = new int[he];
            for (int y = 0; y < he; y++) {
                column[y] = bord[y][x];
            }
            moved |= moveAndMergeLeft(column);
            for (int y = 0; y < he; y++) {
                bord[y][x] = column[y];
            }
        }
        return moved;
    }
    
    private boolean moveDown() {
        boolean moved = false;
        for (int x = 0; x < wi; x++) {
            int[] column = new int[he];
            for (int y = 0; y < he; y++) {
                column[y] = bord[y][x];
            }
            reverse(column);
            moved |= moveAndMergeLeft(column);
            reverse(column);
            for (int y = 0; y < he; y++) {
                bord[y][x] = column[y];
            }
        }
        return moved;
    }
    
    private boolean moveLeft() {
        boolean moved = false;
        for (int y = 0; y < he; y++) {
            int[] row = new int[wi];
            for (int x = 0; x < wi; x++) {
                row[x] = bord[y][x];
            }
            moved |= moveAndMergeLeft(row);
            for (int x = 0; x < wi; x++) {
                bord[y][x] = row[x];
            }
        }
        return moved;
    }
    
    private boolean moveRight() {
        boolean moved = false;
        for (int y = 0; y < he; y++) {
            int[] row = new int[wi];
            for (int x = 0; x < wi; x++) {
                row[x] = bord[y][x];
            }
            reverse(row);
            moved |= moveAndMergeLeft(row);
            reverse(row);
            for (int x = 0; x < wi; x++) {
                bord[y][x] = row[x];
            }
        }
        return moved;
    }
    
    private void reverse(int[] array) {
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
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
