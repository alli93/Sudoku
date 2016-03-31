public class Agent
{
	private State startState;
	// Fixed size while developing
	private int numOfNumbers = 9;
	
	public void init()
	{
		startState = new State(numOfNumbers);
	}
}
