package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import javax.lang.model.element.ModuleElement.DirectiveKind;

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
	private SimulatorInterface game2;
	private SimulatorInterface game3;
	private SimulatorInterface board1;
	private SimulatorInterface board2;
	private SimulatorInterface board3;
	private SimulatorInterface direction;

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0));
		board1 = TTFEFactory.createSimulator(4, 4, new Random(0));

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
   
		board2=TTFEFactory.createSimulator(4, 4, new Random(0));
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				board2.setPieceAt(row, col, 0);  
			}
		}
		board2.setPieceAt(0, 0, 4);
		board2.setPieceAt(0, 2, 2);
		board2.setPieceAt(0, 3, 2);

		board3=TTFEFactory.createSimulator(4, 4, new Random(0));
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				board3.setPieceAt(row, col, 0);  
			}
		}
		board3.setPieceAt(0, 0, 2);
		board3.setPieceAt(0, 1, 2);
		board3.setPieceAt(0, 2, 2);
		board3.setPieceAt(0, 3, 2);

		
		game2=TTFEFactory.createSimulator(4, 4, new Random(0));
		game2.setPieceAt(0, 0, 2);
	   game2.setPieceAt(1, 0, 2);
	   game2.setPieceAt(2, 0, 8);
	   game2.setPieceAt(3, 0, 16);
   
	   game2.setPieceAt(0, 1, 32);
	   game2.setPieceAt(1, 1, 64);
	   game2.setPieceAt(2, 1, 128);
	   game2.setPieceAt(3, 1, 256);
   
	   game2.setPieceAt(0, 2, 512);
	   game2.setPieceAt(1, 2, 1024);
	   game2.setPieceAt(2, 2, 2);
	   game2.setPieceAt(3, 2, 4);
   
	   game2.setPieceAt(0, 3, 8);
	   game2.setPieceAt(1, 3, 16);
	   game2.setPieceAt(2, 3, 32);
	   game2.setPieceAt(3, 3, 64);

	   game3=TTFEFactory.createSimulator(4, 4, new Random(0));
	   for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game3.setPieceAt(row, col, 0);  
		}}
	   direction=TTFEFactory.createSimulator(4, 4, new Random(0));
	   for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			direction.setPieceAt(row, col, 0);  
		}}
	
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
		assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.NORTH));
	   assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.EAST));
	   assertEquals("nodirection", false,board2.isMovePossible(MoveDirection.WEST));

		board2.performMove(MoveDirection.SOUTH);
		assertEquals("uhidsh",4,board2.getPoints());
		assertEquals("uhidsh",4,board2.getPieceAt(0,2));
		assertEquals("move should  possible", true,board2.isMovePossible());

		assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.SOUTH));
		assertEquals("nodirection", false,board2.isMovePossible(MoveDirection.WEST));
	   assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.EAST));
	   assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.NORTH));

		board2.performMove(MoveDirection.NORTH);
		assertEquals("move should  possible", true,board2.isMovePossible());
		assertEquals("uhidsh",12,board2.getPoints());
		assertEquals("uhidsh",8,board2.getPieceAt(0,0));
		
		assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.SOUTH));
		assertEquals("nodirection", false,board2.isMovePossible(MoveDirection.WEST));
	   assertEquals("nodirection", true,board2.isMovePossible(MoveDirection.EAST));
	   assertEquals("nodirection", false,board2.isMovePossible(MoveDirection.NORTH));


		


	}
	@Test
	public void illegalboard3() {
        try {
            board3.isMovePossible(null);
            fail("IllegalArgumentException ");
        } catch (IllegalArgumentException e) {
            
        }
    }
	@Test
	public void board3(){
		assertEquals("move should  possible", true,board3.isMovePossible());
		assertEquals("nodirection", false,board3.isMovePossible(MoveDirection.WEST));
		assertEquals("nodirection", true,board3.isMovePossible(MoveDirection.SOUTH));
	    assertEquals("nodirection", true,board3.isMovePossible(MoveDirection.EAST));
		assertEquals("nodirection", true,board3.isMovePossible(MoveDirection.NORTH));

		assertEquals("nodirection", false,board3.performMove(MoveDirection.WEST));
		board3.performMove(MoveDirection.NORTH);
		assertEquals("kdksjd",8,board3.getPoints());
		assertEquals("point", 4,board3.getPieceAt(0,0));
		assertEquals("point", 4,board3.getPieceAt(0,1));
		
		assertEquals("move should  possible", true,board3.isMovePossible());

		assertEquals("nodirection", false,board3.isMovePossible(MoveDirection.WEST));
		assertEquals("nodirection", true,board3.isMovePossible(MoveDirection.SOUTH));
	    assertEquals("nodirection", true,board3.isMovePossible(MoveDirection.EAST));
		assertEquals("nodirection", true,board3.isMovePossible(MoveDirection.NORTH));

		assertEquals("nodirection", false,board3.performMove(MoveDirection.WEST));
		board3.performMove(MoveDirection.SOUTH);
		assertEquals("kdksjd",16,board3.getPoints());
		assertEquals("point", 0,board3.getPieceAt(0,0));
		assertEquals("point", 8,board3.getPieceAt(0,3));
		assertEquals("nodirection", false,board3.isMovePossible(MoveDirection.SOUTH));
		assertEquals("nodirection", false,board3.isMovePossible(MoveDirection.WEST));
	   assertEquals("nodirection", true,board3.isMovePossible(MoveDirection.EAST));
	   assertEquals("nodirection", true,board3.isMovePossible(MoveDirection.NORTH));

	   assertEquals("nodirection", false,board3.performMove(MoveDirection.WEST));
	   assertEquals("nodirection", false,board3.performMove(MoveDirection.SOUTH));
	}
	
	@Test
	public void movepossgame2(){
		assertEquals("moveshouldbepossible", true,game2.isMovePossible());
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void testMovePossibleWithNullDirection() {
        direction.isMovePossible(null);
    }																			
	@Test
	public void north(){
		
		direction.setPieceAt(0, 3, 8);
		direction.setPieceAt(1, 3, 16);
		direction.setPieceAt(2, 3, 32);
		direction.setPieceAt(3, 3, 64);
		assertTrue("moveposs",true==direction.isMovePossible());
		assertTrue("moveposs",true==direction.isMovePossible(MoveDirection.NORTH));
	   assertEquals("nodirection", false,direction.isMovePossible(MoveDirection.WEST));
	   assertEquals("nodirection", false,direction.isMovePossible(MoveDirection.EAST));
	   assertEquals("nodirection", false,direction.isMovePossible(MoveDirection.SOUTH));

	   assertEquals("nodirection", false,direction.performMove(MoveDirection.WEST));
	   assertEquals("nodirection", false,direction.performMove(MoveDirection.EAST));
	   assertEquals("nodirection", false,direction.performMove(MoveDirection.SOUTH));
		
	}
	@Test
    public void South() {
		direction.setPieceAt(3, 0, 8);
        direction.setPieceAt(2, 0, 16);
        direction.setPieceAt(1, 0, 32);
        direction.setPieceAt(0, 0, 64);
		assertTrue("Mohjsible", direction.isMovePossible());
        assertTrue("Move sojhgpossible", direction.isMovePossible(MoveDirection.SOUTH));
        assertFalse("Move westfje possible", direction.isMovePossible(MoveDirection.WEST));
        assertFalse("Move east sjhgt be possible", direction.isMovePossible(MoveDirection.EAST));
        assertFalse("Move northjhgt be possible", direction.isMovePossible(MoveDirection.NORTH));

        assertFalse("Perform mojhgnot be possible", direction.performMove(MoveDirection.WEST));
        assertFalse("Perform movejhg not be possible", direction.performMove(MoveDirection.EAST));
        assertFalse("Perform movjhgd not be possible", direction.performMove(MoveDirection.NORTH));
    }
	// bis jetyt alles okay
	@Test
    public void East() {
		direction.setPieceAt(0, 0, 8);
        direction.setPieceAt(0, 1, 16);
        direction.setPieceAt(0, 2, 32);
        direction.setPieceAt(0, 3, 64);
		assertTrue("Mohjsible", direction.isMovePossible());
        assertTrue("Move sojhgpossible", direction.isMovePossible(MoveDirection.EAST));
        assertFalse("Move westfje possible", direction.isMovePossible(MoveDirection.WEST));
        assertFalse("Move east sjhgt be possible", direction.isMovePossible(MoveDirection.SOUTH));
        assertFalse("Move northjhgt be possible", direction.isMovePossible(MoveDirection.NORTH));

        assertFalse("Perform mojhgnot be possible", direction.performMove(MoveDirection.WEST));
        assertFalse("Perform movejhg not be possible", direction.performMove(MoveDirection.SOUTH));
        assertFalse("Perform movjhgd not be possible", direction.performMove(MoveDirection.NORTH));
    }
	@Test
    public void West() {
		direction.setPieceAt(3, 0, 8);
        direction.setPieceAt(3, 1, 16);
        direction.setPieceAt(3, 2, 32);
        direction.setPieceAt(3, 3, 64);
		assertTrue("Mohjsible", direction.isMovePossible());
        assertTrue("Move sojhgpossible", direction.isMovePossible(MoveDirection.WEST));
        assertFalse("Move westfje possible", direction.isMovePossible(MoveDirection.EAST));
        assertFalse("Move east sjhgt be possible", direction.isMovePossible(MoveDirection.SOUTH));
        assertFalse("Move northjhgt be possible", direction.isMovePossible(MoveDirection.NORTH));

        assertFalse("Perform mojhgnot be possible", direction.performMove(MoveDirection.EAST));
        assertFalse("Perform movejhg not be possible", direction.performMove(MoveDirection.SOUTH));
        assertFalse("Perform movjhgd not be possible", direction.performMove(MoveDirection.NORTH));
    }
	
@Test
public void getscoresouth(){
	
	
	
		game3.setPieceAt(1, 1, 2);
		game3.setPieceAt(1, 2, 2);
		game3.performMove(MoveDirection.SOUTH);
		
		
		assertEquals("score is wrong",4,game3.getPoints());
		

}
@Test
public void getscorenorth(){
	
	
		game3.setPieceAt(1, 1, 2);
		game3.setPieceAt(1, 2, 2);
		game3.performMove(MoveDirection.NORTH);
		
		assertEquals("score is wrong",4,game3.getPoints());
}
@Test
public void getscoreeast(){
		game3.setPieceAt(1, 1, 2);
		game3.setPieceAt(1, 2, 2);
		game3.performMove(MoveDirection.EAST);
		
		assertEquals("score is wrong",0,game3.getPoints());
}
@Test
public void getscorewest(){
	
	
	
		game3.setPieceAt(1, 1, 2);
		game3.setPieceAt(1, 2, 2);
		game3.performMove(MoveDirection.WEST);
		
		assertEquals("score is wrong",0,game3.getPoints());
		
}
@Test
public void getscoresouthnomerge(){
	
	
		game3.setPieceAt(1, 1, 2);
		game3.setPieceAt(1, 2, 4);
		game3.performMove(MoveDirection.SOUTH);
		
		assertEquals("score is wrong",0,game3.getPoints());
		

} 
@Test
public void getscorenorthnomerge(){
	
	
		game3.setPieceAt(1, 1, 2);
		game3.setPieceAt(1, 2, 4);
		game3.performMove(MoveDirection.NORTH);
		
		assertEquals("score is wrong",0,game3.getPoints());
		

} 

	@Test(expected = IllegalArgumentException.class)
    public void perfmillegal() {
        game3.isMovePossible(null);
	}
	
	@Test
	public void nspo(){
game3.setPieceAt(0, 0, 2);
game3.setPieceAt(1, 0, 4);
game3.setPieceAt(2, 0, 16);
game3.setPieceAt(3, 0, 32);

game3.setPieceAt(0, 1, 32);
game3.setPieceAt(1, 1, 16);
game3.setPieceAt(2, 1, 2);
game3.setPieceAt(3, 1, 4);

game3.setPieceAt(0, 2, 32);
game3.setPieceAt(1, 2, 16);
game3.setPieceAt(2, 2, 2);
game3.setPieceAt(3, 2, 4);

game3.setPieceAt(0, 3, 32);
game3.setPieceAt(1, 3, 16);
game3.setPieceAt(2, 3, 2);
game3.setPieceAt(3, 3, 4);

assertEquals("hgfish",true, game3.isMovePossible());
assertEquals("uhrefai", true,game3.performMove(MoveDirection.EAST));
assertEquals("uhrefai", true,game3.performMove(MoveDirection.WEST));
assertEquals("uhrefai", true,game3.performMove(MoveDirection.NORTH));
assertEquals("uhrefai", true,game3.performMove(MoveDirection.SOUTH));
	}
	


}





 
