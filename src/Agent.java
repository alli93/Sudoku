public class Agent
{
	private State startState;
	// Fixed size while developing
	private int numOfNumbers = 9;
	
	public void init()
	{
		startState = new State(numOfNumbers);
		
		// Initialize a sudoku puzzle, easy difficulty
		// Hardcoded until generator is implemented
		// Link to puzzle: http://www.websudoku.com/?level=1&set_id=9061811194
		startState.board.grid.get(0).get(1).assignment = 6;
		startState.board.grid.get(0).get(4).assignment = 2;
		startState.board.grid.get(0).get(5).assignment = 1;
		startState.board.grid.get(0).get(6).assignment = 9;		
		startState.board.grid.get(1).get(0).assignment = 9;
		startState.board.grid.get(1).get(3).assignment = 7;
		startState.board.grid.get(1).get(4).assignment = 8;
		startState.board.grid.get(1).get(5).assignment = 4;	
		startState.board.grid.get(2).get(0).assignment = 4;
		startState.board.grid.get(2).get(1).assignment = 1;
		startState.board.grid.get(2).get(4).assignment = 5;
		startState.board.grid.get(2).get(8).assignment = 3;	
		startState.board.grid.get(3).get(0).assignment = 6;
		startState.board.grid.get(3).get(3).assignment = 8;
		startState.board.grid.get(3).get(6).assignment = 3;
		startState.board.grid.get(3).get(7).assignment = 9;		
		startState.board.grid.get(4).get(2).assignment = 9;
		startState.board.grid.get(4).get(6).assignment = 5;		
		startState.board.grid.get(5).get(1).assignment = 5;
		startState.board.grid.get(5).get(2).assignment = 3;
		startState.board.grid.get(5).get(5).assignment = 7;
		startState.board.grid.get(5).get(8).assignment = 8;
		startState.board.grid.get(6).get(0).assignment = 3;
		startState.board.grid.get(6).get(4).assignment = 9;
		startState.board.grid.get(6).get(7).assignment = 5;
		startState.board.grid.get(6).get(8).assignment = 2;
		startState.board.grid.get(7).get(3).assignment = 2;
		startState.board.grid.get(7).get(4).assignment = 4;
		startState.board.grid.get(7).get(5).assignment = 8;
		startState.board.grid.get(7).get(8).assignment = 1;	
		startState.board.grid.get(8).get(2).assignment = 1;
		startState.board.grid.get(8).get(3).assignment = 5;
		startState.board.grid.get(8).get(4).assignment = 7;
		startState.board.grid.get(8).get(7).assignment = 4;
		
		System.out.println(startState);
		
	}
}
