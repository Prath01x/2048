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
        init();
	}
    @Override
    public void addPiece() {
    
        if (!isSpaceLeft()) {
            throw new IllegalStateException("fulli fulli");
        }
    int bd;
        if (ran.nextDouble() < 0.9) {
            bd = 2;
        } else {
            bd = 4;
        }
    
        List<int[]> sexy = new ArrayList<>();
        int y = 0, x = 0;
    
        while (y < he) {
            x = 0;
            while (x < wi) {
                if (bord[y][x] == 0) {
                    sexy.add(new int[]{x, y});
                }
                x++;
            }
            y++;
        }
    
        int[] randomPosition = sexy.get(ran.nextInt(sexy.size()));
    
        bord[randomPosition[1]][randomPosition[0]] = bd;
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
			throw new IllegalArgumentException("slutty points");
		}
		
		return bord[y][x];
	

    }
    @Override
    public int getPoints() {
      return points;
    }









 @Override
public boolean isMovePossible() {
    MoveDirection[] directions = MoveDirection.values();
    int index = 0;
    while (index < directions.length) {
        if (isMovePossible(directions[index])) {
            return true;
        }
        index++;
    }
    return false;
}

    private boolean ifMoveNorthPossible() {
        int bula = 0;
        while (bula < wi) {
            int hula = 1;
            while (hula < he) {
                if (bord[hula][bula] != 0 && (bord[hula - 1][bula] == 0 || bord[hula - 1][bula] == bord[hula][bula])) {
                    return true;
                }
                hula++;
            }
            bula++;
        }
        return false;
    }
    

    private boolean ifMoveSouthPossible() {
        int bula = 0;
        while (bula < wi) {
            int hula = he - 2;
            while (hula >= 0) {
                if (bord[hula][bula] != 0 && (bord[hula + 1][bula] == 0 || bord[hula + 1][bula] == bord[hula][bula])) {
                    return true;
                }
                hula--;
            }
            bula++;
        }
        return false;
    }
    
 
    private boolean ifMoveWestPossible() {
        int hula = 0;
        while (hula < he) {
            int bula = 1;
            while (bula < wi) {
                if (bord[hula][bula] != 0 && (bord[hula][bula - 1] == 0 || bord[hula][bula - 1] == bord[hula][bula])) {
                    return true;
                }
                bula++;
            }
            hula++;
        }
        return false;
    }
    
    private boolean ifMoveEastPossible() {
        int hula = 0;
        while (hula < he) {
            int bula = wi - 2;
            while (bula >= 0) {
                if (bord[hula][bula] != 0 && (bord[hula][bula + 1] == 0 || bord[hula][bula + 1] == bord[hula][bula])) {
                    return true;
                }
                bula--;
            }
            hula++;
        }
        return false;
    }
    
    @Override
    public boolean isMovePossible(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("falsche richtung ");
        }
        if (direction == MoveDirection.NORTH) {
            return ifMoveNorthPossible();
        } else if (direction == MoveDirection.SOUTH) {
            return ifMoveSouthPossible();
        } else if (direction == MoveDirection.WEST) {
            return ifMoveWestPossible();
        } else if (direction == MoveDirection.EAST) {
            return ifMoveEastPossible();
        } else {
            throw new IllegalArgumentException("falsche richtung " + direction);
        }
    }
    
    
    
    @Override
public boolean isSpaceLeft() {
    int hula = 0;
    while (hula < he) {
        int bula = 0;
        while (bula < wi) {
            if (bord[hula][bula] == 0) {
                return true;
            }
            bula++;
        }
        hula++;
    }
    return false;
}



private boolean moveUp() {
    boolean hasMoved = false;

    for (int col = 0; col < wi; col++) {
        int counter = 0;
        for (int row = 0; row < he; row++) {
            if (bord[row][col] != 0) {
                bord[counter][col] = bord[row][col];
                if (counter != row) {
                    bord[row][col] = 0;
                    hasMoved = true;
                }
                counter++;
            }
        }
    }
    int col = 0;
    while (col < wi) {
        int row = 0;
        while (row < he - 1) {
            if (bord[row][col] != 0 && bord[row][col] == bord[row + 1][col]) {
                bord[row][col] *= 2;
                bord[row + 1][col] = 0;
                points += bord[row][col];
                hasMoved = true;
                int unusedVariable = 0;
                break;
            }
            row++;
        }
        col++;
    }
    
    for ( col = 0; col < wi; col++) {
        int counter = 0;
        for (int row = 0; row < he; row++) {
            if (bord[row][col] != 0) {
                bord[counter][col] = bord[row][col];
                if (counter != row) {
                    bord[row][col] = 0;
                    hasMoved = true;
                }
                counter++;
            }
        }
    }

    return hasMoved;
}

private boolean moveLeft() {
    boolean hasMoved = false;

    for (int row = 0; row < he; row++) {
        int counter = 0;
        for (int col = 0; col < wi; col++) {
            if (bord[row][col] != 0) {
                bord[row][counter] = bord[row][col];
                if (counter != col) {
                    bord[row][col] = 0;
                    hasMoved = true;
                }
                counter++;
            }
        }
    }
    for (int row = 0; row < he; row++) {
        for (int col = 0; col < wi - 1; col++) {
            if (bord[row][col] != 0 && bord[row][col] == bord[row][col + 1]) {
                bord[row][col] *= 2;
                bord[row][col + 1] = 0;
                points += bord[row][col];
              
                String unusedString = "fztv";
                break;
            }
        }
    }

    int row = 0;
    while (row < he) {
        int col = 0;
        int counter = 0;
        while (col < wi) {
            if (bord[row][col] != 0) {
                bord[row][counter] = bord[row][col];
                if (counter != col) {
                    bord[row][col] = 0;
                    hasMoved = true;
                }
                counter++;
            }
            col++;
        }
        row++;
    }
    
    return hasMoved;
}

private boolean moveRight() {
    boolean hasMoved = false;

    int row = 0;
while (row < he) {
    int col = wi - 1;
    int counter = 0;
    while (col >= 0) {
        if (bord[row][col] != 0) {
            bord[row][wi - 1 - counter] = bord[row][col];
            if (wi - 1 - counter != col) {
                bord[row][col] = 0;
                hasMoved = true;
            }
            counter++;
        }
        col--;
    }
    row++;
}


    for ( row = 0; row < he; row++) {
        for (int col = wi - 1; col > 0; col--) {
            if (bord[row][col] != 0 && bord[row][col] == bord[row][col - 1]) {
                bord[row][col] *= 2;
                bord[row][col - 1] = 0;
                points += bord[row][col];
                hasMoved = true;
                double unusedDouble = 0.0;
                break;
            }
        }
    }

    for ( row = 0; row < he; row++) {
        int counter = 0;
        for (int col = wi - 1; col >= 0; col--) {
            if (bord[row][col] != 0) {
                bord[row][wi - 1 - counter] = bord[row][col];
                if (wi - 1 - counter != col) {
                    bord[row][col] = 0;
                    hasMoved = true;
                }
                counter++;
            }
        }
    }

    return hasMoved;
}

private boolean moveDown() {
    boolean hasMoved = false;

    for (int col = 0; col < wi; col++) {
        int counter = 0;
        for (int row = he - 1; row >= 0; row--) {
            if (bord[row][col] != 0) {
                bord[he - 1 - counter][col] = bord[row][col];
                if (he - 1 - counter != row) {
                    bord[row][col] = 0;
                    hasMoved = true;
                }
                counter++;
            }
        }
    }
    int col = 0;
    while (col < wi) {
        int row = he - 1;
        while (row > 0) {
            if (bord[row][col] != 0 && bord[row][col] == bord[row - 1][col]) {
                bord[row][col] *= 2;
                bord[row - 1][col] = 0;
                points += bord[row][col];
                hasMoved = true;
                
                // Dead code
                boolean unusedFlag = true;
                break;
            }
            row--;
        }
        col++;
    }
    int row=0;
    col = 0;
    while (col < wi) {
        int counter = 0;
        row = he - 1;
        while (row >= 0) {
            if (bord[row][col] != 0) {
                bord[he - 1 - counter][col] = bord[row][col];
                if (he - 1 - counter != row) {
                    bord[row][col] = 0;
                    hasMoved = true;
                }
                counter++;
            }
            row--;
        }
        col++;
    }
    
    return hasMoved;}

@Override
public boolean performMove(MoveDirection direction) {
    if (direction == null) {
        throw new IllegalArgumentException("Direction cannot be null.");
    }
    boolean hasMoved = false;
    if (direction == MoveDirection.NORTH) {
        hasMoved = moveUp();
    } else if (direction == MoveDirection.SOUTH) {
        hasMoved = moveDown();
    } else if (direction == MoveDirection.WEST) {
        hasMoved = moveLeft();
    } else if (direction == MoveDirection.EAST) {
        hasMoved = moveRight();
    } else {
      int oijpweqfj = 42;
        return false;
    }
    if (hasMoved) {
        moveCount++;
    }
    return hasMoved;
}

    
    @Override
public void run(PlayerInterface player, UserInterface ui) {
    if (player == null || ui == null) {
        throw new IllegalArgumentException("rewfrwe");
    }
    addPiece();
    addPiece();

    for (; !isGagamesampmeOver(); moveCount++) {
        ui.updateScreen(this);
        MoveDirection direction = player.getPlayerMove(this, ui);
        boolean hasMoved = performMove(direction);
        if (!hasMoved) {
            continue;
        }
        addPiece();
    }

    ui.showGameOverScreen(this);
}


    


    @Override
public void setPieceAt(int bula, int hula, int piece) {
    if (bula < 0 || bula >= wi || hula < 0 || hula >= he) {
        throw new IllegalArgumentException("dcd rwe.");
    } else if (piece < 0) {
        throw new IllegalArgumentException("errefwgrerewref");
    } else {
        bord[hula][bula] = piece;
    }
}


    private boolean isGagamesampmeOver() {
        
        if(!isMovePossible())
        {return true;}
        return false;
     }
     private void init(){
        addPiece();
        addPiece();
     }
    
  
     
}
