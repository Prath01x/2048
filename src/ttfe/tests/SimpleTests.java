package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;

/**
 * This class provides a very simple example of how to write tests for this project.
 * You can implement your own tests within this class or any other class within this package.
 * Tests in other packages will not be run and considered for completion of the project.
 */
public class SimpleTests {

	private SimulatorInterface game;
	private SimulatorInterface board1;
	private SimulatorInterface board2;

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0));
		board1 = TTFEFactory.createSimulator(4, 4, new Random(0));
		board2=TTFEFactory.createSimulator(4, 4, new Random(0));
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				board2.setPieceAt(row, col, 0);  
			}
		}
		board2.setPieceAt(0, 0, 4);
		board2.setPieceAt(0, 2, 2);
		board2.setPieceAt(0, 3, 2);

		board1.setPieceAt(0, 0, 2);
	   board1.setPieceAt(1, 0, 4);
	   board1.setPieceAt(2, 0, 0);
	   board1.setPieceAt(3, 0, 0);
   
	   board1.setPieceAt(0, 1, 4);
	   board1.setPieceAt(1, 1, 8);
	   board1.setPieceAt(2, 1, 0);
	   board1.setPieceAt(3, 1, 0);
   
	   board1.setPieceAt(0, 2, 16);
	   board1.setPieceAt(1, 2, 32);
	   board1.setPieceAt(2, 2, 4);
	   board1.setPieceAt(3, 2, 2);
   
	   board1.setPieceAt(0, 3, 2);
	   board1.setPieceAt(1, 3, 2048);
	   board1.setPieceAt(2, 3, 8);
	   board1.setPieceAt(3, 3, 128);
   
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



 
@Test
public void twopiece1(){
	for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game.setPieceAt(row, col, 0);  
		}
	}
	game.setPieceAt(0, 0, 2);
	game.setPieceAt(0, 1, 2);
	assertEquals("should merge two tile", true,game.isMovePossible());
}

@Test
public void twopiece2(){
	for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game.setPieceAt(row, col, 0);  
		}
	}
	game.setPieceAt(0, 0, 2);
	game.setPieceAt(0, 1, 2);
	assertEquals("north poss", true,game.isMovePossible(MoveDirection.NORTH));
}
@Test
public void twopiece3(){
	for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game.setPieceAt(row, col, 0);  
		}
	}
	game.setPieceAt(0, 0, 2);
	game.setPieceAt(0, 1, 2);
	assertEquals("east poss", true,game.isMovePossible(MoveDirection.EAST));
}
@Test
public void twopiece4(){
	for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game.setPieceAt(row, col, 0);  
		}
	}
	game.setPieceAt(0, 0, 2);
	game.setPieceAt(0, 1, 2);
	assertEquals("east poss", false,game.isMovePossible(MoveDirection.WEST));
}
@Test
public void twopiece5(){
	for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game.setPieceAt(row, col, 0);  
		}
	}
	game.setPieceAt(0, 0, 2);
	game.setPieceAt(0, 1, 2);
	assertEquals("east poss", true,game.isMovePossible(MoveDirection.SOUTH));
}
@Test
    public void illegalgame() {
        try {
            game.isMovePossible(null);
            fail("IllegalArgumentException ");
        } catch (IllegalArgumentException e) {
            
        }
    }

	@Test 
	public void movepoo2(){
   
	   for (int row = 0; row < 4; row++) {
		   for (int col = 0; col < 4; col++) {
			   game.setPieceAt(row, col, 0);  
		   } }
	   game.setPieceAt(0, 0, 2);
	   game.setPieceAt(1, 0, 4);
	   game.setPieceAt(2, 0, 8);
	   game.setPieceAt(3, 0, 16);
   
	   game.setPieceAt(0, 1, 32);
	   game.setPieceAt(1, 1, 64);
	   game.setPieceAt(2, 1, 128);
	   game.setPieceAt(3, 1, 256);
   
	   game.setPieceAt(0, 2, 512);
	   game.setPieceAt(1, 2, 1024);
	   game.setPieceAt(2, 2, 512);
	   game.setPieceAt(3, 2, 16);
   
	   game.setPieceAt(0, 3, 32);
	   game.setPieceAt(1, 3, 64);
	   game.setPieceAt(2, 3, 8);
	   game.setPieceAt(3, 3, 128);
   
   
   
	   assertEquals("move should not be possible", false,game.isMovePossible());
	   assertEquals("nodirection", false,game.isMovePossible(MoveDirection.NORTH));
	   assertEquals("nodirection", false,game.isMovePossible(MoveDirection.EAST));
	   assertEquals("nodirection", false,game.isMovePossible(MoveDirection.WEST));
	   assertEquals("nodirection", false,game.isMovePossible(MoveDirection.SOUTH));
	}

	@Test 
	public void movepoo3(){
   
	 
       
	   assertEquals("move should not be possible", true,board1.isMovePossible());
	   assertEquals("nodirection", true,board1.isMovePossible(MoveDirection.NORTH));
	   assertEquals("nodirection", true,board1.isMovePossible(MoveDirection.EAST));
	   assertEquals("nodirection", false,board1.isMovePossible(MoveDirection.WEST));
	   assertEquals("nodirection", false,board1.isMovePossible(MoveDirection.SOUTH));
	}
	@Test
	public void illegalboard1() {
        try {
            board1.isMovePossible(null);
            fail("IllegalArgumentException ");
        } catch (IllegalArgumentException e) {
            
        }
    }
	@Test
	public void illegalboard2() {
        try {
            board2.isMovePossible(null);
            fail("IllegalArgumentException ");
        } catch (IllegalArgumentException e) {
            
        }
    }
	@Test
	public void board2(){
		assertEquals("move should  possible", true,board2.isMovePossible());
		assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.SOUTH));
		board2.performMove(MoveDirection.SOUTH);
		assertEquals("uhidsh",4,board2.getPieceAt(0,2));

		assertEquals("move should  possible", true,board2.isMovePossible());
		assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.NORTH));
		board2.performMove(MoveDirection.NORTH);
		assertEquals("uhidsh",8,board2.getPieceAt(0,0));


	}
}





 
