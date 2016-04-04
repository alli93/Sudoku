import java.util.ArrayList;

public class Agent
{
	private State puzzle9x9Easy;
	private State puzzle9x9Evil;
	private State puzzle4x4Hard;
	private State puzzle16x16Evil;
	private State puzzleToSolve;

	private int numOfNumbers4 = 4;
	private int numOfNumbers9 = 9;
	private int numOfNumbers16 = 16;

	// Default value
	private int puzzleNumber = 1;

	public void init()
	{
		puzzle9x9Easy = new State(numOfNumbers9);
		puzzle4x4Hard = new State(numOfNumbers4);
		puzzle9x9Evil = new State(numOfNumbers9);
		puzzle16x16Evil = new State(numOfNumbers16);

		// Initialize a 9x9 sudoku puzzle, easy difficulty
		// Link to puzzle: http://www.websudoku.com/?level=1&set_id=9061811194
		puzzle9x9Easy.assignVariable(new Position(0, 1), 6);
		puzzle9x9Easy.assignVariable(new Position(0, 4), 2);
		puzzle9x9Easy.assignVariable(new Position(0, 5), 1);
		puzzle9x9Easy.assignVariable(new Position(0, 6), 9);
		puzzle9x9Easy.assignVariable(new Position(1, 0), 9);
		puzzle9x9Easy.assignVariable(new Position(1, 3), 7);
		puzzle9x9Easy.assignVariable(new Position(1, 4), 8);
		puzzle9x9Easy.assignVariable(new Position(1, 5), 4);
		puzzle9x9Easy.assignVariable(new Position(2, 0), 4);
		puzzle9x9Easy.assignVariable(new Position(2, 1), 1);
		puzzle9x9Easy.assignVariable(new Position(2, 4), 5);
		puzzle9x9Easy.assignVariable(new Position(2, 8), 3);
		puzzle9x9Easy.assignVariable(new Position(3, 0), 6);
		puzzle9x9Easy.assignVariable(new Position(3, 3), 8);
		puzzle9x9Easy.assignVariable(new Position(3, 6), 3);
		puzzle9x9Easy.assignVariable(new Position(3, 7), 9);
		puzzle9x9Easy.assignVariable(new Position(4, 2), 9);
		puzzle9x9Easy.assignVariable(new Position(4, 6), 5);
		puzzle9x9Easy.assignVariable(new Position(5, 1), 5);
		puzzle9x9Easy.assignVariable(new Position(5, 2), 3);
		puzzle9x9Easy.assignVariable(new Position(5, 5), 7);
		puzzle9x9Easy.assignVariable(new Position(5, 8), 8);
		puzzle9x9Easy.assignVariable(new Position(6, 0), 3);
		puzzle9x9Easy.assignVariable(new Position(6, 4), 9);
		puzzle9x9Easy.assignVariable(new Position(6, 7), 5);
		puzzle9x9Easy.assignVariable(new Position(6, 8), 2);
		puzzle9x9Easy.assignVariable(new Position(7, 3), 2);
		puzzle9x9Easy.assignVariable(new Position(7, 4), 4);
		puzzle9x9Easy.assignVariable(new Position(7, 5), 8);
		puzzle9x9Easy.assignVariable(new Position(7, 8), 1);
		puzzle9x9Easy.assignVariable(new Position(8, 2), 1);
		puzzle9x9Easy.assignVariable(new Position(8, 3), 5);
		puzzle9x9Easy.assignVariable(new Position(8, 4), 7);
		puzzle9x9Easy.assignVariable(new Position(8, 7), 4);
		puzzle9x9Easy.cellsChangedInForwardChecking.clear();

		// Initialize a 4x4 sudoku puzzle, hard difficulty
		puzzle4x4Hard.assignVariable(new Position(0, 0), 3);
		puzzle4x4Hard.assignVariable(new Position(1, 1), 4);
		puzzle4x4Hard.assignVariable(new Position(2, 2), 2);
		puzzle4x4Hard.assignVariable(new Position(3, 1), 3);
		puzzle4x4Hard.assignVariable(new Position(3, 3), 1);
		puzzle4x4Hard.cellsChangedInForwardChecking.clear();

		// Initialize a 9x9 sudoku puzzle, evil difficulty
		// Link to puzzle: http://www.websudoku.com/?level=4&set_id=8426889781
		puzzle9x9Evil.assignVariable(new Position(0, 0), 3);
		puzzle9x9Evil.assignVariable(new Position(0, 2), 5);
		puzzle9x9Evil.assignVariable(new Position(0, 4), 8);
		puzzle9x9Evil.assignVariable(new Position(1, 4), 1);
		puzzle9x9Evil.assignVariable(new Position(1, 7), 8);
		puzzle9x9Evil.assignVariable(new Position(2, 2), 2);
		puzzle9x9Evil.assignVariable(new Position(2, 5), 7);
		puzzle9x9Evil.assignVariable(new Position(2, 7), 6);
		puzzle9x9Evil.assignVariable(new Position(3, 2), 9);
		puzzle9x9Evil.assignVariable(new Position(3, 3), 3);
		puzzle9x9Evil.assignVariable(new Position(3, 6), 5);
		puzzle9x9Evil.assignVariable(new Position(4, 1), 3);
		puzzle9x9Evil.assignVariable(new Position(4, 4), 7);
		puzzle9x9Evil.assignVariable(new Position(4, 7), 2);
		puzzle9x9Evil.assignVariable(new Position(5, 2), 8);
		puzzle9x9Evil.assignVariable(new Position(5, 5), 4);
		puzzle9x9Evil.assignVariable(new Position(5, 6), 1);
		puzzle9x9Evil.assignVariable(new Position(6, 1), 2);
		puzzle9x9Evil.assignVariable(new Position(6, 3), 1);
		puzzle9x9Evil.assignVariable(new Position(6, 6), 9);
		puzzle9x9Evil.assignVariable(new Position(7, 1), 9);
		puzzle9x9Evil.assignVariable(new Position(7, 4), 3);
		puzzle9x9Evil.assignVariable(new Position(8, 4), 6);
		puzzle9x9Evil.assignVariable(new Position(8, 6), 4);
		puzzle9x9Evil.assignVariable(new Position(8, 8), 3);
		puzzle9x9Evil.cellsChangedInForwardChecking.clear();

		// Initialize a 16x16 sudoku puzzle, evil difficulty
		// Link to puzzle: http://gary.appenzeller.net/Sudoku/Sudoku17.htm
		puzzle16x16Evil.assignVariable(new Position(0, 0), 4);
		puzzle16x16Evil.assignVariable(new Position(0, 1), 8);
		puzzle16x16Evil.assignVariable(new Position(0, 2), 1);
		puzzle16x16Evil.assignVariable(new Position(0, 6), 6);
		puzzle16x16Evil.assignVariable(new Position(0, 7), 7);
		puzzle16x16Evil.assignVariable(new Position(0, 8), 14);
		puzzle16x16Evil.assignVariable(new Position(0, 10), 15);
		puzzle16x16Evil.assignVariable(new Position(0, 11), 5);
		puzzle16x16Evil.assignVariable(new Position(0, 13), 2);
		puzzle16x16Evil.assignVariable(new Position(0, 15), 10);

		puzzle16x16Evil.assignVariable(new Position(1, 0), 3);
		puzzle16x16Evil.assignVariable(new Position(1, 8), 10);
		puzzle16x16Evil.assignVariable(new Position(1, 9), 6);
		puzzle16x16Evil.assignVariable(new Position(1, 12), 15);
		puzzle16x16Evil.assignVariable(new Position(1, 15), 7);

		puzzle16x16Evil.assignVariable(new Position(2, 0), 15);
		puzzle16x16Evil.assignVariable(new Position(2, 3), 11);
		puzzle16x16Evil.assignVariable(new Position(2, 4), 10);
		puzzle16x16Evil.assignVariable(new Position(2, 7), 14);
		puzzle16x16Evil.assignVariable(new Position(2, 8), 13);
		puzzle16x16Evil.assignVariable(new Position(2, 13), 4);
		puzzle16x16Evil.assignVariable(new Position(2, 14), 1);

		puzzle16x16Evil.assignVariable(new Position(3, 2), 5);
		puzzle16x16Evil.assignVariable(new Position(3, 3), 7);
		puzzle16x16Evil.assignVariable(new Position(3, 4), 9);
		puzzle16x16Evil.assignVariable(new Position(3, 6), 15);
		puzzle16x16Evil.assignVariable(new Position(3, 9), 12);

		puzzle16x16Evil.assignVariable(new Position(4, 1), 13);
		puzzle16x16Evil.assignVariable(new Position(4, 4), 15);
		puzzle16x16Evil.assignVariable(new Position(4, 5), 8);
		puzzle16x16Evil.assignVariable(new Position(4, 6), 16);
		puzzle16x16Evil.assignVariable(new Position(4, 8), 7);
		puzzle16x16Evil.assignVariable(new Position(4, 9), 1);
		puzzle16x16Evil.assignVariable(new Position(4, 13), 3);

		puzzle16x16Evil.assignVariable(new Position(5, 1), 7);
		puzzle16x16Evil.assignVariable(new Position(5, 2), 15);
		puzzle16x16Evil.assignVariable(new Position(5, 3), 8);
		puzzle16x16Evil.assignVariable(new Position(5, 5), 14);
		puzzle16x16Evil.assignVariable(new Position(5, 8), 16);
		puzzle16x16Evil.assignVariable(new Position(5, 10), 4);
		puzzle16x16Evil.assignVariable(new Position(5, 11), 13);
		puzzle16x16Evil.assignVariable(new Position(5, 13), 5);

		puzzle16x16Evil.assignVariable(new Position(6, 0), 9);
		puzzle16x16Evil.assignVariable(new Position(6, 3), 3);
		puzzle16x16Evil.assignVariable(new Position(6, 4), 7);
		puzzle16x16Evil.assignVariable(new Position(6, 5), 1);
		puzzle16x16Evil.assignVariable(new Position(6, 6), 4);
		puzzle16x16Evil.assignVariable(new Position(6, 7), 5);
		puzzle16x16Evil.assignVariable(new Position(6, 8), 8);

		puzzle16x16Evil.assignVariable(new Position(7, 0), 16);
		puzzle16x16Evil.assignVariable(new Position(7, 8), 15);
		puzzle16x16Evil.assignVariable(new Position(7, 9), 3);
		puzzle16x16Evil.assignVariable(new Position(7, 13), 9);
		puzzle16x16Evil.assignVariable(new Position(7, 15), 2);

		puzzle16x16Evil.assignVariable(new Position(8, 0), 14);
		puzzle16x16Evil.assignVariable(new Position(8, 2), 10);
		puzzle16x16Evil.assignVariable(new Position(8, 6), 7);
		puzzle16x16Evil.assignVariable(new Position(8, 7), 15);
		puzzle16x16Evil.assignVariable(new Position(8, 15), 9);

		puzzle16x16Evil.assignVariable(new Position(9, 7), 13);
		puzzle16x16Evil.assignVariable(new Position(9, 8), 3);
		puzzle16x16Evil.assignVariable(new Position(9, 9), 16);
		puzzle16x16Evil.assignVariable(new Position(9, 10), 11);
		puzzle16x16Evil.assignVariable(new Position(9, 11), 8);
		puzzle16x16Evil.assignVariable(new Position(9, 12), 6);
		puzzle16x16Evil.assignVariable(new Position(9, 15), 1);

		puzzle16x16Evil.assignVariable(new Position(10, 2), 9);
		puzzle16x16Evil.assignVariable(new Position(10, 4), 6);
		puzzle16x16Evil.assignVariable(new Position(10, 5), 12);
		puzzle16x16Evil.assignVariable(new Position(10, 7), 16);
		puzzle16x16Evil.assignVariable(new Position(10, 10), 14);
		puzzle16x16Evil.assignVariable(new Position(10, 12), 7);
		puzzle16x16Evil.assignVariable(new Position(10, 13), 10);
		puzzle16x16Evil.assignVariable(new Position(10, 14), 3);

		puzzle16x16Evil.assignVariable(new Position(11, 2), 3);
		puzzle16x16Evil.assignVariable(new Position(11, 6), 14);
		puzzle16x16Evil.assignVariable(new Position(11, 7), 2);
		puzzle16x16Evil.assignVariable(new Position(11, 9), 15);
		puzzle16x16Evil.assignVariable(new Position(11, 10), 10);
		puzzle16x16Evil.assignVariable(new Position(11, 11), 7);
		puzzle16x16Evil.assignVariable(new Position(11, 14), 11);

		puzzle16x16Evil.assignVariable(new Position(12, 6), 11);
		puzzle16x16Evil.assignVariable(new Position(12, 9), 8);
		puzzle16x16Evil.assignVariable(new Position(12, 11), 9);
		puzzle16x16Evil.assignVariable(new Position(12, 12), 14);
		puzzle16x16Evil.assignVariable(new Position(12, 13), 15);

		puzzle16x16Evil.assignVariable(new Position(13, 1), 15);
		puzzle16x16Evil.assignVariable(new Position(13, 2), 8);
		puzzle16x16Evil.assignVariable(new Position(13, 7), 9);
		puzzle16x16Evil.assignVariable(new Position(13, 8), 5);
		puzzle16x16Evil.assignVariable(new Position(13, 11), 6);
		puzzle16x16Evil.assignVariable(new Position(13, 12), 16);

		puzzle16x16Evil.assignVariable(new Position(14, 0), 12);
		puzzle16x16Evil.assignVariable(new Position(14, 3), 9);
		puzzle16x16Evil.assignVariable(new Position(14, 6), 3);
		puzzle16x16Evil.assignVariable(new Position(14, 7), 10);
		puzzle16x16Evil.assignVariable(new Position(14, 15), 8);

		puzzle16x16Evil.assignVariable(new Position(15, 0), 7);
		puzzle16x16Evil.assignVariable(new Position(15, 2), 4);
		puzzle16x16Evil.assignVariable(new Position(15, 4), 16);
		puzzle16x16Evil.assignVariable(new Position(15, 5), 15);
		puzzle16x16Evil.assignVariable(new Position(15, 7), 12);
		puzzle16x16Evil.assignVariable(new Position(15, 8), 2);
		puzzle16x16Evil.assignVariable(new Position(15, 9), 10);
		puzzle16x16Evil.assignVariable(new Position(15, 13), 6);
		puzzle16x16Evil.assignVariable(new Position(15, 14), 9);
		puzzle16x16Evil.assignVariable(new Position(15, 15), 11);
	}

	public State solvePuzzle()
	{
		if (puzzleNumber == 1)
		{
			puzzleToSolve = puzzle4x4Hard;
		} else if (puzzleNumber == 2)
		{
			puzzleToSolve = puzzle9x9Easy;
		} else if (puzzleNumber == 4)
		{
			puzzleToSolve = puzzle16x16Evil;
		} else
		{
			puzzleToSolve = puzzle9x9Evil;
		}

		System.out.println("Puzzle:");
		System.out.println(puzzleToSolve);
		System.out.println();
		State solution = csp_backtracking(puzzleToSolve);

		return solution;
	}

	public State csp_backtracking(State currentState)
	{
		// Check if the current state is goal state
		if (currentState.isGoalState())
		{
			return currentState;
		}

		// Get the position of the most constrained variable
		Position posOfMostConstrainedVariable = currentState.positionOfMostConstrainedVariable();

		// Order the valid assigments according to the least-constraining-value
		// heuristic
		ArrayList<Integer> values = currentState.leastConstrainingValues(posOfMostConstrainedVariable);

		for (int i = 0; i < values.size(); i++)
		{
			State result = State.newInstance(currentState);
			result.cellsChangedInForwardChecking.clear();
			result.assignVariable(posOfMostConstrainedVariable, values.get(i));

			currentState.cellsChangedInForwardChecking.clear();
			// Add value to variable and do forward checking
			currentState.assignVariable(posOfMostConstrainedVariable, values.get(i));

			if (!currentState.unassignedVariableHasAnEmptyDomain())
			{
				result = csp_backtracking(result);
				if (result.isGoalState())
				{
					return result;
				}
			}
			currentState.unAssignVariable(posOfMostConstrainedVariable, values.get(i));
		}

		return currentState;
	}

	public void setPuzzleNumber(int number)
	{
		this.puzzleNumber = number;
	}
}
