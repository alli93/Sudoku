import java.util.ArrayList;

public class Agent
{
	private State startState;
	private State startState2;
	// Fixed size while developing
	private int numOfNumbers = 9;
	
	private int numOfNumbers2 = 4;

	public void init()
	{
		startState = new State(numOfNumbers);
		startState2 = new State(numOfNumbers2);

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
		startState.cellsChangedInForwardChecking.clear();		

		/*System.out.println(startState.board.grid.get(0).get(0).validAssignments);
		System.out.println(startState.numOfValuesRemovedByAssignment(new Position(0, 0), 5));
		System.out.println(startState.numOfValuesRemovedByAssignment(new Position(0, 0), 7));
		System.out.println(startState.numOfValuesRemovedByAssignment(new Position(0, 0), 8));
		System.out.println(startState.leastConstrainingValues(new Position(0, 0)));
		System.out.println(startState.leastConstrainingValues(new Position(2, 5)));*/
		
		startState2.assignVariable(new Position(0, 0), 3);
		startState2.assignVariable(new Position(1, 1), 4);
		startState2.assignVariable(new Position(2, 2), 2);
		startState2.assignVariable(new Position(3, 1), 3);
		startState2.assignVariable(new Position(3, 3), 1);
		startState2.cellsChangedInForwardChecking.clear();

	}

	public State solvePuzzle()
	{
		System.out.println("Puzzle:");
		System.out.println(startState);
		System.out.println();
		State solution = csp_backtracking(startState);
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

}
