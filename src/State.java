import java.util.ArrayList;

public class State
{
	public Grid board;
	public int numOfNumbers;
	public int numOfAssignedVariables = 0;

	public class Grid
	{
		ArrayList<ArrayList<Variable>> grid = new ArrayList<ArrayList<Variable>>();

		public class Variable
		{
			public int domainSize;
			public int assignment;
			ArrayList<Integer> validAssignments = new ArrayList<Integer>();

			public Variable()
			{
				domainSize = numOfNumbers;
				assignment = 0;
				for (int i = 1; i <= domainSize; i++)
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
		this.board.grid.get(pos.row).get(pos.column).assignment = number;
		this.numOfAssignedVariables++;
	}

	public State(int numOfNumbers)
	{
		this.numOfNumbers = numOfNumbers;
		this.board = new Grid(numOfNumbers);
	}

	public boolean isValidAssignment(Position pos, int number)
	{

		// Check if the row already contains the number
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			if (board.grid.get(pos.row).get(i).assignment == number)
			{
				return false;
			}
		}

		// Check if the column already contains the number
		for (int i = 0; i < this.numOfNumbers; i++)
		{
			if (board.grid.get(i).get(pos.column).assignment == number)
			{
				return false;
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
				if (board.grid.get(i + subgridRow).get(j + subgridColumn).assignment == number)
				{
					return false;
				}
			}
		}

		return true;
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
