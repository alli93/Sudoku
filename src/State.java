import java.util.ArrayList;

public class State {
	public Grid board;
	public int numOfNumbers;
	
	public class Grid {
		ArrayList<ArrayList<Variable>> grid = new ArrayList<ArrayList<Variable>>();
		
		public class Variable {
			public int domainSize;
			public int assignment;
			ArrayList<Integer> validAssignments = new ArrayList<Integer>();
			
			public Variable() {
				domainSize = numOfNumbers;
				assignment = 0;
				for(int i = 1; i <= domainSize; i++) {
					validAssignments.add(i);
				}
			}
		}
		public Grid(int numOfNumbers){
			for(int i = 0; i < numOfNumbers; i++) {
				grid.add(new ArrayList<Variable>());
				for(int j = 0; j < numOfNumbers; j++) {
					grid.get(i).add(new Variable());
				}
			}
		}
	}
	public State(int numOfNumbers) {
		this.numOfNumbers = numOfNumbers;
		this.board = new Grid(numOfNumbers);
	}
}
