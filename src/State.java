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
