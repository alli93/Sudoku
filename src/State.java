import java.util.ArrayList;

public class State
{
	public Grid board;
	public int numOfNumbers;
	public int numOfAssignedVariables = 0;
	public ArrayList<Position> cellsChangedInForwardChecking = new ArrayList<Position>();

	public State(int numberOfNumbers)
	{
		numOfNumbers = numberOfNumbers;
		board = new Grid(numOfNumbers);
	}

	public static State newInstance(State oldstate)
	{
		State newState = new State(oldstate.numOfNumbers);

		for (int i = 0; i < oldstate.board.grid.size(); i++)
		{
			for (int j = 0; j < oldstate.numOfNumbers; j++)
			{
				newState.board.grid.get(i).set(j,
						oldstate.board.grid.get(i).get(j).newInstance(oldstate.board.grid.get(i).get(j)));
			}
		}
		newState.numOfNumbers = oldstate.numOfNumbers;
		newState.numOfAssignedVariables = oldstate.numOfAssignedVariables;
		for (int i = 0; i < oldstate.cellsChangedInForwardChecking.size(); i++)
		{
			newState.cellsChangedInForwardChecking.add(oldstate.cellsChangedInForwardChecking.get(i));
		}
		return newState;
	}

	public class Grid
	{
		ArrayList<ArrayList<Variable>> grid = new ArrayList<ArrayList<Variable>>();

		public class Variable
		{
			public int assignment;
			ArrayList<Integer> validAssignments = new ArrayList<Integer>();

			public Variable()
			{
				assignment = 0;
				for (int i = 1; i <= numOfNumbers; i++)
				{
					validAssignments.add(i);
				}
			}

			public Variable newInstance(Variable oldVariable)
			{
				Variable newVariable = new Variable();
				newVariable.validAssignments.clear();

				newVariable.assignment = oldVariable.assignment;
				for (int i = 0; i < oldVariable.validAssignments.size(); i++)
				{
					int currenAssignment = oldVariable.validAssignments.get(i);
					newVariable.validAssignments.add(currenAssignment);
				}

				return newVariable;
			}

		}

		public Grid(int numOfNumbers)
		{
			for (int i = 0; i < numOfNumbers; i++)
			{
				grid.add(new ArrayList<Variable>());
				for (int j = 0; j < numOfNumbers; j++)
				{
					grid.get(i).add(new Variable());
				}
			}
		}
	}

	public void assignVariable(Position pos, int number)
	{
		// Assign the number to the cell
		this.board.grid.get(pos.row).get(pos.column).assignment = number;

		// Update the number of assigned variables
		this.numOfAssignedVariables++;

		// Update the valid assignments of all the variables in the same row
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			if (board.grid.get(pos.row).get(i).assignment == 0
					&& board.grid.get(pos.row).get(i).validAssignments.remove(new Integer(number)))
			{
				cellsChangedInForwardChecking.add(new Position(pos.row, i));
			}
		}

		// Update the valid assignments of all the variables in the same column
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			if (board.grid.get(i).get(pos.column).assignment == 0
					&& board.grid.get(i).get(pos.column).validAssignments.remove(new Integer(number)))
			{
				cellsChangedInForwardChecking.add(new Position(i, pos.column));
			}
		}

		// Check if the subgrid already contains the number
		int numOfRowsInSubgrid = (int) Math.sqrt((double) this.numOfNumbers);
		int numOfColumnsInSubgrid = (int) Math.sqrt((double) this.numOfNumbers);
		int subgridColumn = (pos.column / numOfColumnsInSubgrid) * numOfColumnsInSubgrid;
		int subgridRow = (pos.row / numOfRowsInSubgrid) * numOfRowsInSubgrid;

		for (int i = 0; i < numOfRowsInSubgrid; i++)
		{
			for (int j = 0; j < numOfColumnsInSubgrid; j++)
			{
				if (board.grid.get(i + subgridRow).get(j + subgridColumn).assignment == 0
						&& board.grid.get(i + subgridRow).get(j + subgridColumn).validAssignments
								.remove(new Integer(number)))
				{
					cellsChangedInForwardChecking.add(new Position(i + subgridRow, j + subgridColumn));
				}
			}
		}
	}

	public int numOfValuesRemovedByAssignment(Position pos, int number)
	{
		int numOfValuesRemoved = 0;

		ArrayList<Position> alreadyVisited = new ArrayList<Position>();

		// Update the valid assignments of all the variables in the same row
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			if (board.grid.get(pos.row).get(i).validAssignments.contains(new Integer(number))
					&& board.grid.get(pos.row).get(i).assignment == 0
					&& !alreadyVisited.contains(new Position(pos.row, i)))
			{
				numOfValuesRemoved++;
				alreadyVisited.add(new Position(pos.row, i));
			}

		}

		// Update the valid assignments of all the variables in the same column
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			if (board.grid.get(i).get(pos.column).validAssignments.contains(new Integer(number))
					&& board.grid.get(i).get(pos.column).assignment == 0
					&& !alreadyVisited.contains(new Position(i, pos.column)))
			{
				numOfValuesRemoved++;
				alreadyVisited.add(new Position(i, pos.column));
			}
		}

		// Check if the subgrid already contains the number
		int numOfRowsInSubgrid = (int) Math.sqrt((double) this.numOfNumbers);
		int numOfColumnsInSubgrid = (int) Math.sqrt((double) this.numOfNumbers);
		int subgridColumn = (pos.column / numOfColumnsInSubgrid) * numOfColumnsInSubgrid;
		int subgridRow = (pos.row / numOfRowsInSubgrid) * numOfRowsInSubgrid;

		for (int i = 0; i < numOfRowsInSubgrid; i++)
		{
			for (int j = 0; j < numOfColumnsInSubgrid; j++)
			{
				if (board.grid.get(i + subgridRow).get(j + subgridColumn).validAssignments.contains(new Integer(number))
						&& board.grid.get(i + subgridRow).get(j + subgridColumn).assignment == 0
						&& !alreadyVisited.contains(new Position(i + subgridRow, j + subgridColumn)))
				{
					numOfValuesRemoved++;
					alreadyVisited.add(new Position(i + subgridRow, j + subgridColumn));
				}
			}
		}

		return numOfValuesRemoved;
	}

	public void unAssignVariable(Position pos, int number)
	{
		Position cellPos;

		this.numOfAssignedVariables--;

		// Remove the assignment from the cell
		this.board.grid.get(pos.row).get(pos.column).assignment = 0;

		// Update the variables that valid assignments changed due to the
		// variable being assigned
		for (int i = 0; i < cellsChangedInForwardChecking.size(); i++)
		{
			cellPos = cellsChangedInForwardChecking.get(i);
			this.board.grid.get(cellPos.row).get(cellPos.column).validAssignments.add(number);
		}
	}

	public Position positionOfMostConstrainedVariable()
	{
		Position mostConstrained = new Position(0, 0);
		int minValidAssignments = Integer.MAX_VALUE;

		for (int i = 0; i < numOfNumbers; i++)
		{
			for (int j = 0; j < numOfNumbers; j++)
			{
				// Find the most constrained variable that is unassigned
				if ((this.board.grid.get(i).get(j).validAssignments.size() < minValidAssignments)
						&& (this.board.grid.get(i).get(j).assignment == 0))
				{
					minValidAssignments = this.board.grid.get(i).get(j).validAssignments.size();
					mostConstrained.row = i;
					mostConstrained.column = j;
				}
			}
		}

		return mostConstrained;
	}

	// Returns a list, ordered by least-constraining values
	public ArrayList<Integer> leastConstrainingValues(Position pos)
	{
		ArrayList<Integer> leastConstrainingValues = new ArrayList<Integer>();
		ArrayList<Integer> validAssignments = this.board.grid.get(pos.row).get(pos.column).validAssignments;
		ArrayList<Integer> numOfValuesRemoved = new ArrayList<Integer>();
		int currentNumber = 0;

		// Initialize numOfValuesRemoved with zeros
		for (int i = 0; i < numOfNumbers; i++)
		{
			numOfValuesRemoved.add(0);
		}

		// Find the number of values removed by each valid assignment
		for (int i = 0; i < validAssignments.size(); i++)
		{
			currentNumber = validAssignments.get(i);
			numOfValuesRemoved.set(currentNumber - 1, numOfValuesRemovedByAssignment(pos, currentNumber));
		}

		int currentMinValuesRemoved = Integer.MAX_VALUE;
		int currentMinValue = 0;

		for (int i = 0; i < validAssignments.size(); i++)
		{
			for (int j = 0; j < validAssignments.size(); j++)
			{
				if (numOfValuesRemoved.get(validAssignments.get(j) - 1) < currentMinValuesRemoved)
				{
					currentMinValuesRemoved = numOfValuesRemoved.get(validAssignments.get(j) - 1);
					currentMinValue = validAssignments.get(j);
				}
			}
			leastConstrainingValues.add(currentMinValue);
			numOfValuesRemoved.set(currentMinValue - 1, Integer.MAX_VALUE);
			currentMinValuesRemoved = Integer.MAX_VALUE;
		}

		return leastConstrainingValues;
	}

	public boolean unassignedVariableHasAnEmptyDomain()
	{
		// Check if any variable has an empty domain
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			for (int j = 0; j < this.numOfNumbers; j++)
			{
				if (this.board.grid.get(i).get(j).assignment == 0
						&& this.board.grid.get(i).get(j).validAssignments.isEmpty())
				{
					return true;
				}
			}
		}

		return false;
	}

	public boolean isGoalState()
	{
		return numOfAssignedVariables == (numOfNumbers * numOfNumbers);
	}

	public boolean checkSolution(State solution)
	{
		if (!isGoalState())
		{
			return false;
		}

		ArrayList<Integer> numbersSeen = new ArrayList<Integer>();

		// Check that there is a unique variable in each row
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			numbersSeen.clear();
			// Add each variable in the row to numbersSeen
			for (int j = 0; j < this.numOfNumbers; j++)
			{
				numbersSeen.add(new Integer(board.grid.get(i).get(j).assignment));
			}

			// Check that each variable seen in row is unique
			for (int j = 1; j <= this.numOfNumbers; j++)
			{
				if (!numbersSeen.contains(new Integer(j)))
				{
					return false;
				}
			}
		}

		// Check that there is a unique variable in each column
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			numbersSeen.clear();
			// Add each variable in the column to numbersSeen
			for (int j = 0; j < this.numOfNumbers; j++)
			{
				numbersSeen.add(new Integer(board.grid.get(j).get(i).assignment));
			}

			// Check that each variable seen in column is unique
			for (int j = 1; j <= this.numOfNumbers; j++)
			{
				if (!numbersSeen.contains(new Integer(j)))
				{
					return false;
				}
			}
		}

		// Check if the subgrid already contains the number
		int numOfRowsInSubgrid = (int) Math.sqrt((double) this.numOfNumbers);
		int numOfColumnsInSubgrid = (int) Math.sqrt((double) this.numOfNumbers);
		int currentRow = 0;
		int currentColumn = 0;
		int numOfSubgridsCheckedInRow = 0;

		// Go through each subgrid
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			if (numOfSubgridsCheckedInRow == numOfRowsInSubgrid)
			{
				currentRow += numOfRowsInSubgrid;
				numOfSubgridsCheckedInRow = 0;
			}
			numbersSeen.clear();
			// Add each variable in the subgrid to numbersSeen
			for (int j = 0; j < numOfRowsInSubgrid; j++)
			{
				for (int k = 0; k < numOfColumnsInSubgrid; k++)
				{
					numbersSeen.add(new Integer(board.grid.get(j + currentRow).get(k + currentColumn).assignment));
				}
			}

			// Check that each variable seen in subgrid is unique
			for (int j = 1; j <= this.numOfNumbers; j++)
			{
				if (!numbersSeen.contains(new Integer(j)))
				{
					return false;
				}
			}
			
			currentColumn += numOfColumnsInSubgrid;
			currentColumn = currentColumn % this.numOfNumbers;
			numOfSubgridsCheckedInRow++;
		}

		return true;
	}

	@Override
	public String toString()
	{
		StringBuffer board = new StringBuffer();

		for (int j = 0; j < numOfNumbers; ++j)
		{
			board.append("+---");
		}
		board.append("+\n");
		for (int i = numOfNumbers - 1; i >= 0; --i)
		{
			for (int j = 0; j < numOfNumbers; ++j)
			{
				if (this.board.grid.get(i).get(j).assignment == 0)
				{
					board.append("|   ");
				} else
				{
					board.append("| " + this.board.grid.get(i).get(j).assignment + " ");
				}
			}
			board.append("| \n");
			if (i != 0)
			{
				for (int j = 0; j < numOfNumbers; ++j)
				{
					board.append("+---");
				}
				board.append("+\n");
			}
		}
		for (int j = 0; j < numOfNumbers; ++j)
		{
			board.append("+---");
		}
		board.append("+\n");
		return (board.toString());
	}
}
