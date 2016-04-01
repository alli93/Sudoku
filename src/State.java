import java.util.ArrayList;

public class State
{
	public Grid board;
	public int numOfNumbers;
	public int numOfAssignedVariables = 0;
	public ArrayList<Position> cellsChangedInForwardChecking = new ArrayList<Position>();

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
			if (board.grid.get(pos.row).get(i).validAssignments.remove(new Integer(number)))
			{
				cellsChangedInForwardChecking.add(new Position(pos.row, i));
			}
		}

		// Update the valid assignments of all the variables in the same column
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			if (board.grid.get(i).get(pos.column).validAssignments.remove(new Integer(number)))
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
				if (board.grid.get(i + subgridRow).get(j + subgridColumn).validAssignments.remove(new Integer(number)))
				{
					cellsChangedInForwardChecking.add(new Position(i + subgridRow, j + subgridColumn));
				}
			}
		}
	}

	public void unAssignVariable(Position pos, int number)
	{
		Position cellPos;
		
		// Remove the assignment from the cell
		this.board.grid.get(pos.row).get(pos.column).assignment = 0;
		
		// Update the variables that valid assignments changed due to the variable being assigned
		for (int i = 0; i < cellsChangedInForwardChecking.size(); i++)
		{
			cellPos = cellsChangedInForwardChecking.get(i);
			this.board.grid.get(cellPos.row).get(cellPos.column).validAssignments.add(number);
		}
	}

	public State(int numOfNumbers)
	{
		this.numOfNumbers = numOfNumbers;
		this.board = new Grid(numOfNumbers);
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
