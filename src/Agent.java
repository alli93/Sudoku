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
		startState.assignVariable(new Position(0, 1), 6);
		startState.assignVariable(new Position(0, 4), 2);
		startState.assignVariable(new Position(0, 5), 1);
		startState.assignVariable(new Position(0, 6), 9);
		startState.assignVariable(new Position(1, 0), 9);
		startState.assignVariable(new Position(1, 3), 7);
		startState.assignVariable(new Position(1, 4), 8);
		startState.assignVariable(new Position(1, 5), 4);
		startState.assignVariable(new Position(2, 0), 4);
		startState.assignVariable(new Position(2, 1), 1);
		startState.assignVariable(new Position(2, 4), 5);
		startState.assignVariable(new Position(2, 8), 3);
		startState.assignVariable(new Position(3, 0), 6);
		startState.assignVariable(new Position(3, 3), 8);
		startState.assignVariable(new Position(3, 6), 3);
		startState.assignVariable(new Position(3, 7), 9);
		startState.assignVariable(new Position(4, 2), 9);
		startState.assignVariable(new Position(4, 6), 5);
		startState.assignVariable(new Position(5, 1), 5);
		startState.assignVariable(new Position(5, 2), 3);
		startState.assignVariable(new Position(5, 5), 7);
		startState.assignVariable(new Position(5, 8), 8);
		startState.assignVariable(new Position(6, 0), 3);
		startState.assignVariable(new Position(6, 4), 9);
		startState.assignVariable(new Position(6, 7), 5);
		startState.assignVariable(new Position(6, 8), 2);
		startState.assignVariable(new Position(7, 3), 2);
		startState.assignVariable(new Position(7, 4), 4);
		startState.assignVariable(new Position(7, 5), 8);
		startState.assignVariable(new Position(7, 8), 1);
		startState.assignVariable(new Position(8, 2), 1);
		startState.assignVariable(new Position(8, 3), 5);
		startState.assignVariable(new Position(8, 4), 7);
		startState.assignVariable(new Position(8, 7), 4);

		System.out.println(startState);

	}

	public State solvePuzzle()
	{
		State solution = csp_backtracking(startState);
		return solution;
	}

	public State csp_backtracking(State currentState)
	{
		if (currentState.isGoalState())
		{
			return currentState;
		}
		
		
		return currentState;
	}

}
