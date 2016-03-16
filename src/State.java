public class State {
	public Grid board;
	
	public class Grid {
		Variable[][] grid; // grid[row][column]
		
		public class Variable {
			public int domainSize;
			public int assignment;
			
			public Variable() {
				domainSize = grid.length;
				assignment = -1;
			}
			
			public void assign(int assignment) {
				this.assignment = assignment;
			}
		}
		public Grid(){
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid.length; j++) {
					grid[i][j] = new Variable();
				}
			}
		}

		public Variable[] getRow(Position p) {
			if(p.getRow() >= grid.length) {
				throw new IndexOutOfBoundsException();
			}
			Variable[] row = new Variable[grid.length];
			for(int i = 0; i < grid.length; i++) {
				row[i] = grid[p.getRow()][i];
			}
			return row;
		}
		
		public Variable[] getColumn(Position p) {
			if(p.getColumn() >= grid.length) {
				throw new IndexOutOfBoundsException();
			}
			Variable[] column = new Variable[grid.length];
			for(int i = 0; i < grid.length; i++) {
				column[i] = grid[i][p.getColumn()];
			}
			return column;
		}
		
		public Variable[] getSquare(Position p) {
			if(p.getColumn() >= grid.length || p.getRow() >= grid.length) {
				throw new IndexOutOfBoundsException();
			}
			Variable[] square = new Variable[grid.length];
			int squareSize = (int) Math.sqrt((double) grid.length);
			int index = 0;
			for(int i = (p.getRow() - (p.getRow() % squareSize)); i % squareSize != 0; i++) {
				for(int j = (p.getColumn() - (p.getColumn() % squareSize)); j % squareSize != 0; j++) {
					square[index] = grid[i][j];
					index++;
				}
			}
			return square;
		}
	}
	public State() {
		
	}
}
