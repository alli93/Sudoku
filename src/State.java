import java.util.ArrayList;

public class State {
	public Grid board;
	
	public class Grid {
		Variable[][] grid; // grid[row][column]
		
		public class Variable {
			public int domainSize;
			public int assignment;
			ArrayList<Integer> validAssignments;
			
			public Variable() {
				domainSize = grid.length;
				assignment = 0;
				for(int i = 1; i <= domainSize; i++) {
					validAssignments.add(i);
				}
			}
		}
		public Grid(){
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid.length; j++) {
					grid[i][j] = new Variable();
				}
			}
		}
	}
	public State() {
		board = new Grid();
	}
}
