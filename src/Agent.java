import java.util.ArrayList;

public class Agent
{
	private State puzzle9x9Easy;
	private State puzzle9x9Evil;
	private State puzzle4x4Hard;
	private State puzzleToSolve;

	// Fixed size while developing
	private int numOfNumbers = 9;

	private int numOfNumbers2 = 4;
	private int puzzleNumber = 1;

	public void init()
	{
		puzzle9x9Easy = new State(numOfNumbers);
		puzzle4x4Hard = new State(numOfNumbers2);
		puzzle9x9Evil = new State(numOfNumbers);

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
	}

	public State solvePuzzle()
	{
		if (puzzleNumber == 1)
		{
			puzzleToSolve = puzzle4x4Hard;
		} else if (puzzleNumber == 2)
		{
			puzzleToSolve = puzzle9x9Easy;
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
