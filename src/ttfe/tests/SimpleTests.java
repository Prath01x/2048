package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;

/**
 * This class provides a very simple example of how to write tests for this project.
 * You can implement your own tests within this class or any other class within this package.
 * Tests in other packages will not be run and considered for completion of the project.
 */
public class SimpleTests {

	private SimulatorInterface game;

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0));
	}
	
	@Test
	public void testInitialGamePoints() {
		assertEquals("The initial game did not have zero points", 0,
				game.getPoints());
	}
	
	@Test
	public void testInitialBoardHeight() {
		assertTrue("The initial game board did not have correct height",
				4 == game.getBoardHeight());
	}
	//my tests
	//Pieceadd checking piecer after and before
	@Test
	public void padd(){
		
		game.addPiece();
		int piecepehla= game.getNumPieces();
		game.addPiece();
		int piecebaad= game.getNumPieces();
		assertEquals("only piece must be added", piecepehla+1,piecebaad);
	}
	//if board is full
	@Test
	public void padd1(){
		int pa=game.getNumPieces();
		if (pa == 16) {
			try {	
				game.addPiece();
				fail("chutiaaa");
			} catch (IllegalStateException e) {
			
			}
		}
		else{
			game.addPiece();
			int piecebaad= game.getNumPieces();
			assertEquals("lund mera", pa+1,piecebaad);

		}
	}
	@Test
	public void padd2(){
		for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                game.setPieceAt(row, col, 8);  // Set a non-2 and non-4 value, e.g., 8
            }
			game.addPiece();

			// Check if the 4th row contains only 2 or 4
			boolean isValidRow = true;
			for (int col = 0; col < 4; col++) {
				int piece = game.getPieceAt(3, col);  // Check row 3 (index 3 is the 4th row)
				if (piece != 2 && piece != 4) {
					isValidRow = false;
					break;
				}
			}
	
			assertTrue("Pieces added to row 3 should have a value of 2 or 4", isValidRow);
		}
	}	
        




		
	

}