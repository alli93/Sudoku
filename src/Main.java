import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		Agent agent = new Agent();
		int puzzleNumber;
		System.out.println("Welcome to Sudoku solver");
		System.out.println("Enter the number of the puzzle to solve:");
		System.out.println("1 - 4x4 puzzle");
		System.out.println("2 - 9x9 puzzle");
		puzzleNumber = in.nextInt();

		agent.setPuzzleNumber(puzzleNumber);
		agent.init();

		long startTime = System.currentTimeMillis();
		State solution = agent.solvePuzzle();
		if (solution.isGoalState())
		{
			System.out.println("Solution:");
			System.out.println(solution);
			System.out.println();
			System.out.println("Solution found in " + (System.currentTimeMillis() - startTime) + " ms");
		} else
		{
			System.out.println("No solution found. Runtime: " + (System.currentTimeMillis() - startTime) + " ms");
		}

		in.close();
	}

}
