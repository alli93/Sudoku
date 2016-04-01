
public class Main
{

	public static void main(String[] args)
	{
		Agent agent = new Agent();
		agent.init();
		State solution = agent.solvePuzzle();
		System.out.println(solution);

	}

}
