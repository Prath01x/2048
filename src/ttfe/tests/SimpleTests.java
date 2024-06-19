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


	//test for pove possible ohne richtung
 @Test 
 public void movepoo(){
	
	for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game.setPieceAt(row, col, 0);  
		}
	}
	game.setPieceAt(1, 1, 2);
	game.setPieceAt(2, 1, 2);
	assertEquals("space availaible", true,game.isMovePossible());

 }
 @Test 
 public void movepoo1(){
	
	for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game.setPieceAt(row, col, 2);  
		}
	}
	game.setPieceAt(1, 1, 0);

	assertEquals("move and merge both possible", true,game.isMovePossible());

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

 }
 @Test 
 public void movepoo3(){
	
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
		game.setPieceAt(2, 2, 16);
		game.setPieceAt(3, 2, 16);
	
		game.setPieceAt(0, 3, 32);
		game.setPieceAt(1, 3, 64);
		game.setPieceAt(2, 3, 8);
		game.setPieceAt(3, 3, 128);



	assertEquals("one move is possible", true,game.isMovePossible());

 }
    ///test for pove possible mit richtung richtung
 @Test 
 public void movepoorichtungnordenno(){
	
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
	assertEquals("no space availaible and no tiles", false,game.isMovePossible(MoveDirection.NORTH));
 }

 @Test 
 public void movepoorichtungnordenyes(){
	
	for (int row = 0; row < 4; row++) {
		for (int col = 0; col < 4; col++) {
			game.setPieceAt(row, col, 0);  
		} }
		
	
	game.setPieceAt(1, 0, 4);
	game.setPieceAt(2, 0, 8);
	game.setPieceAt(3, 0, 16);


	game.setPieceAt(1, 1, 64);
	game.setPieceAt(2, 1, 128);
	game.setPieceAt(3, 1, 256);

	
	game.setPieceAt(1, 2, 1024);
	game.setPieceAt(2, 2, 512);
	game.setPieceAt(3, 2, 16);

	
	game.setPieceAt(1, 3, 64);
	game.setPieceAt(2, 3, 8);
	game.setPieceAt(3, 3, 128);
	assertEquals("space availaible but not tile", true,game.isMovePossible(MoveDirection.NORTH));

 }



		
	

}